package com.cnx.newsfeed.data.newsSet

import androidx.paging.PageKeyedDataSource
import com.cnx.newsfeed.api.NewsListModel
import com.cnx.newsfeed.api.NewsListResponse
import com.cnx.newsfeed.common.apiKey
import com.cnx.newsfeed.data.Result
import com.cnx.newsfeed.data.dao.NewsDao
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class NewsPageDataSource @Inject constructor(

    private val remoteDataSource: NewsRemoteDataSource,
    private val newsDao: NewsDao,
    private val coroutineScope: CoroutineScope

) : PageKeyedDataSource<Int,NewsListModel>() {


    override fun loadInitial (
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsListModel>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsListModel>) {

        val page = params.key
        fetchData(page, params.requestedLoadSize) {
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

            val response : com.cnx.newsfeed.data.Result<NewsListResponse> = remoteDataSource.fetchNewsList(apiKey, page, pageSize)

            if (response.status == com.cnx.newsfeed.data.Result.Status.SUCCESS) {
                val results = response.data?.articles ?: emptyList()
                newsDao.insertAll(results )
                callback(results)

            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
       // networkState.postValue(NetworkState.FAILED)
    }


}