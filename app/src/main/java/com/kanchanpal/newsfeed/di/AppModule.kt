package com.kanchanpal.newsfeed.di

import android.app.Application
import com.kanchanpal.newsfeed.api.NewsService
import com.kanchanpal.newsfeed.api.RetrofitFactory
import com.kanchanpal.newsfeed.data.AppDatabase
import com.kanchanpal.newsfeed.data.newsSet.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesNewsService(): NewsService = RetrofitFactory.getNewsService()

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsService: NewsService)
            = NewsRemoteDataSource(newsService)

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsSetDao(db: AppDatabase) = db.getNewsListDao()
}
