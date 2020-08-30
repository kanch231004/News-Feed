package com.kanchanpal.newsfeed.util

import com.kanchanpal.newsfeed.api.NewsListModel


/** Fake entries to be entered in dao. Dao should return same values when we call get() */

val title = "Title1"

val testNewsA = NewsListModel("Title1", "UrlToImage1", "Description1",
        "Author1",  "url1", "publishedAt1")

val testNewsB = NewsListModel("Title2", "UrlToImage2", "Description2",
        "Author2", "url2", "publishedAt2")

