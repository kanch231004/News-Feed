package com.kanchanpal.newsfeed.data.newsSet

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.kanchanpal.newsfeed.api.NewsListModel
import com.kanchanpal.newsfeed.data.dao.NewsDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NewsPageDataSourceFactory @Inject constructor(
    private val dataSource: NewsRemoteDataSource,
    private val dao: NewsDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, NewsListModel>() {

    val liveData = MutableLiveData<NewsPageDataSource>()

    override fun create(): DataSource<Int, NewsListModel> {
        val source = NewsPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}
