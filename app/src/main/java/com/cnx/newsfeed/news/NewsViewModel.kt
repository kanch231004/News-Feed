package com.cnx.newsfeed.news

import androidx.lifecycle.ViewModel
import com.cnx.newsfeed.data.newsSet.NewsRepository
import javax.inject.Inject


class NewsViewModel @Inject constructor(repository: NewsRepository): ViewModel() {

    val newsList = repository.news


}