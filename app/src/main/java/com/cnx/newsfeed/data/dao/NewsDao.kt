package com.cnx.newsfeed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cnx.newsfeed.api.NewsListModel

@Dao
interface NewsDao {

    @Query("Select * from NewsListModel")
    fun getNews() : LiveData<List<NewsListModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(feeds: List<NewsListModel>)

}