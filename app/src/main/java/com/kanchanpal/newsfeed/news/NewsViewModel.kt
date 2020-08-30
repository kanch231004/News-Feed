package com.kanchanpal.newsfeed.news

import androidx.lifecycle.ViewModel
import com.kanchanpal.newsfeed.api.Data
import com.kanchanpal.newsfeed.api.NewsListModel
import com.kanchanpal.newsfeed.data.newsSet.NewsRepository
import com.kanchanpal.newsfeed.di.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    private var newsList: Data<NewsListModel>? = null

    fun newsList(connectivityAvailable: Boolean): Data<NewsListModel>? {

        if (newsList == null) {
            newsList = repository.observePagedNews(connectivityAvailable, ioCoroutineScope)
        }
        return newsList
    }

    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
