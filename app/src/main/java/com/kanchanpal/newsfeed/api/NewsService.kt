package com.kanchanpal.newsfeed.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

object RetrofitFactory {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { it ->
                Timber.d("NewsService $it")
            }).setLevel(HttpLoggingInterceptor.Level.BODY)
    ).build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(NewsService.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsService() = retrofit.create(NewsService::class.java)
}

interface NewsService {

    @GET("/v2/everything")
    suspend fun getTopNewsList(

        @Query("apiKey") apiKey: String? = null, @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("q") source: String? = null
    ): Response<NewsListResponse>

    companion object {
        const val ENDPOINT = "https://newsapi.org/"
    }
}
