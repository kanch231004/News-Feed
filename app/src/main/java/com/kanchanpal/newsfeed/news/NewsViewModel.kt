package com.kanchanpal.newsfeed.news

import androidx.lifecycle.ViewModel
import com.kanchanpal.newsfeed.api.Data
import com.kanchanpal.newsfeed.api.NewsListModel
import com.kanchanpal.newsfeed.data.newsSet.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {
    private var newsList: Data<NewsListModel>? = null
    private val ioCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
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
