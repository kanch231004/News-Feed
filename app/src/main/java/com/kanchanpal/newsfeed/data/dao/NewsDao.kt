package com.kanchanpal.newsfeed.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanchanpal.newsfeed.api.NewsListModel

@Dao
interface NewsDao {
    @Query("Select * from NewsListModel")
    fun getNews() : LiveData<List<NewsListModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAll(newsList: List<NewsListModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: NewsListModel)

    @Query("SELECT * FROM NewsListModel")
    fun getPagedNews(): DataSource.Factory<Int, NewsListModel>
}
