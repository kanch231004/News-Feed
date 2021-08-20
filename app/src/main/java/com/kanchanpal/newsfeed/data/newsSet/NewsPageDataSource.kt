package com.kanchanpal.newsfeed.data.newsSet

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.kanchanpal.newsfeed.api.NetworkState
import com.kanchanpal.newsfeed.api.NewsListModel
import com.kanchanpal.newsfeed.common.apiKey
import com.kanchanpal.newsfeed.data.Result
import com.kanchanpal.newsfeed.data.dao.NewsDao
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class NewsPageDataSource @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val newsDao: NewsDao,
    private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Int, NewsListModel>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsListModel>
    ) {
        networkState.postValue(NetworkState.LOADING)
        fetchData(page = 1, pageSize = params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsListModel>) {
        networkState.postValue(NetworkState.LOADING)
        val page = params.key
        fetchData(page = page, pageSize = params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsListModel>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<NewsListModel>) -> Unit) {
        coroutineScope.launch(getJobErrorHandler()) {
            when (val response = remoteDataSource.fetchNewsList(apiKey, page, pageSize)) {
                is Result.Error -> {
                    networkState.postValue(NetworkState.error(response.message ?: "Unknown error"))
                    postError(response.message)
                }
                is Result.Success -> {
                    val results = response.data.articles
                    newsDao.insertAll(results)
                    callback(results)
                    networkState.postValue(NetworkState.LOADED)
                }
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String?) {
        Timber.e("An error happened: $message")
    }
}
