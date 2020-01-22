package com.cnx.newsfeed.data.newsSet

import com.cnx.newsfeed.api.BaseDataSource
import com.cnx.newsfeed.api.NewsListResponse
import com.cnx.newsfeed.api.NewsService
import com.cnx.newsfeed.common.KEYWORD_BITCOIN
import com.cnx.newsfeed.data.Result
import javax.inject.Inject

/**
 * Works with the News API to get data.
 */


class NewsRemoteDataSource @Inject constructor( val service: NewsService) : BaseDataSource() {

    suspend fun fetchNewsList(apiKey : String, page : Int, pageSize : Int ) : Result<NewsListResponse> {

        return getResult { service.getTopNewsList(apiKey, page,pageSize, KEYWORD_BITCOIN) }
    }

}
