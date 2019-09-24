package com.cnx.newsfeed.data.newsSet

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cnx.newsfeed.api.NewsListModel
import com.cnx.newsfeed.data.dao.NewsDao
import com.cnx.newsfeed.testing.OpenForTesting
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@OpenForTesting
class NewsRepository @Inject constructor(private val newsDao: NewsDao,
                                         private val newsRemoteDataSource: NewsRemoteDataSource) {



    fun observePagedNews(connectivityAvailable : Boolean, coroutineScope: CoroutineScope) =
        if (connectivityAvailable)
            observeRemotePagedNews(coroutineScope)
        else observeLocalPagedNews()


    private fun observeLocalPagedNews(): LiveData<PagedList<NewsListModel>> {

        val dataSourceFactory =
            newsDao.getPagedNews()

        return LivePagedListBuilder(dataSourceFactory,
            NewsPageDataSourceFactory.pagedListConfig()).build()
    }


    private fun observeRemotePagedNews(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<NewsListModel>> {

        val dataSourceFactory = NewsPageDataSourceFactory(newsRemoteDataSource,
            newsDao, ioCoroutineScope)

        return LivePagedListBuilder(dataSourceFactory,
            NewsPageDataSourceFactory.pagedListConfig()).build()
    }


}