package com.kanchanpal.newsfeed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kanchanpal.newsfeed.api.NewsListModel
import com.kanchanpal.newsfeed.data.dao.NewsDao

/**
 * The Room database for this app
 */
@Database(entities = [NewsListModel::class],
        version = 1, exportSchema = false)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsListDao(): NewsDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        private fun buildDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(context, AppDatabase::class.java, "news-db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }).build()
        }
    }
}
