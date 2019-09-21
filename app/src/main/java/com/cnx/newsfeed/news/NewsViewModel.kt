package com.cnx.newsfeed.news

import androidx.lifecycle.ViewModel
import com.cnx.newsfeed.data.newsSet.NewsRepository
import com.cnx.newsfeed.di.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class NewsViewModel @Inject constructor(repository: NewsRepository,
                                        @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope): ViewModel() {

    var connectivityAvailable : Boolean = false

    val newsList = repository.observePagedNews(connectivityAvailable,ioCoroutineScope)


}