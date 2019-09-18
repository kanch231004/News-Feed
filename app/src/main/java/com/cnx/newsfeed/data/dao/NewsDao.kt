package com.cnx.newsfeed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cnx.newsfeed.api.NewsListModel

@Dao
abstract class NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(newsList : List<NewsListModel>)


    @Query("Select * From NewsListModel")
    abstract fun getNewsList() : LiveData<List<NewsListModel>>



}