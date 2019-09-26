package com.cnx.newsfeed.news

import androidx.lifecycle.ViewModel
import com.cnx.newsfeed.api.Data
import com.cnx.newsfeed.api.NewsListModel
import com.cnx.newsfeed.data.newsSet.NewsRepository
import com.cnx.newsfeed.di.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject


class NewsViewModel @Inject constructor(private val repository: NewsRepository,
                                        @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope): ViewModel() {


      var newsList : Data<NewsListModel>? = null

    fun newsList(connectivityAvailable : Boolean) : Data<NewsListModel>? {

        if (newsList == null) {
            newsList = repository.observePagedNews(connectivityAvailable, ioCoroutineScope)

        }

        return  newsList

    }


    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }


}