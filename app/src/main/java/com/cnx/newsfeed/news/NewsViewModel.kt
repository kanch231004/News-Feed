package com.cnx.newsfeed.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.cnx.newsfeed.api.NewsListModel
import com.cnx.newsfeed.data.newsSet.NewsRepository
import com.cnx.newsfeed.di.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class NewsViewModel @Inject constructor(private val repository: NewsRepository,
                                        @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope): ViewModel() {


    fun newsList(connectivityAvailable : Boolean) : LiveData<PagedList<NewsListModel>> {

        Log.d("isConnected ","${connectivityAvailable}")
       return repository.observePagedNews(connectivityAvailable, ioCoroutineScope)

    }


}