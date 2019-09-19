package com.cnx.newsfeed.data.newsSet

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cnx.newsfeed.api.NewsListModel
import com.cnx.newsfeed.common.apiKey
import com.cnx.newsfeed.data.dao.NewsDao
import com.cnx.newsfeed.data.resultLiveData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDao: NewsDao,
                                         private val newsRemoteDataSource: NewsRemoteDataSource) {


    val news =
        resultLiveData( databaseQuery = { newsDao.getNews() },
            networkCall = {newsRemoteDataSource.fetchNewsList(apiKey,1,25)},
            saveCallResult =  {newsDao.insertAll(it.articles)})


  /*  fun observePagedNews(id: String) = resultLiveData(
        databaseQuery = { newsDao.getNews() },
        networkCall = { newsRemoteDataSource.fetchSet(id) },
        saveCallResult = { newsDao.insertAll(it) })
        .distinctUntilChanged()*/


   /* private fun observeLocalPagedSets(themeId: Int? = null): LiveData<PagedList<LegoSet>> {

        val dataSourceFactory =
            if (themeId == null) dao.getPagedLegoSets()
            else newsDao.getPagedLegoSetsByTheme(themeId)

        return LivePagedListBuilder(dataSourceFactory,
            LegoSetPageDataSourceFactory.pagedListConfig()).build()
    }*/


    private fun observeRemotePagedSets( ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<NewsListModel>> {

        val dataSourceFactory = NewsPageDataSourceFactory(newsRemoteDataSource,
            newsDao, ioCoroutineScope)

        return LivePagedListBuilder(dataSourceFactory,
            NewsPageDataSourceFactory.pagedListConfig()).build()
    }


    companion object {

        const val PAGE_SIZE = 25

        // For Singleton instantiation
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(dao: NewsDao, legoSetRemoteDataSource: NewsRemoteDataSource) =
            instance ?: synchronized(this) {
                instance
                    ?: NewsRepository(dao, legoSetRemoteDataSource).also { instance = it }
            }
    }
}