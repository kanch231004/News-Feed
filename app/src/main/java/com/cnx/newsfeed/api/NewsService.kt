package com.cnx.newsfeed.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    companion object {
        const val ENDPOINT = "https://newsapi.org/"
    }


    @GET("/v2/top-headlines")
    suspend fun getTopNewsList(

        @Query("apiKey") apiKey: String, @Query("page") page : Int
        ,@Query("pageSize") pageSize : Int,
        @Query("q") source: String = "bitcoin"): Response<NewsListResponse>

}