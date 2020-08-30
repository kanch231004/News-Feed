package com.kanchanpal.newsfeed.data.newsSet

import com.kanchanpal.newsfeed.api.BaseDataSource
import com.kanchanpal.newsfeed.api.NewsListResponse
import com.kanchanpal.newsfeed.api.NewsService
import com.kanchanpal.newsfeed.common.KEYWORD_BITCOIN
import com.kanchanpal.newsfeed.data.Result
import javax.inject.Inject

/**
 * Works with the News API to get data.
 */

class NewsRemoteDataSource @Inject constructor( val service: NewsService) : BaseDataSource() {

    suspend fun fetchNewsList(apiKey : String, page : Int, pageSize : Int ) : Result<NewsListResponse> {
        return getResult { service.getTopNewsList(apiKey, page,pageSize, KEYWORD_BITCOIN) }
    }
}
