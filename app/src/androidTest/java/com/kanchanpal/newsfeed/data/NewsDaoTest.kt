package com.kanchanpal.newsfeed.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kanchanpal.newsfeed.data.dao.NewsDao
import com.kanchanpal.newsfeed.util.getValue
import com.kanchanpal.newsfeed.util.testNewsA
import com.kanchanpal.newsfeed.util.testNewsB
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest : DbTest() {
    private lateinit var newsDao: NewsDao
    private val setA = testNewsA.copy()
    private val setB = testNewsB.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        newsDao = db.getNewsListDao()

        // Insert legoSets in non-alphabetical order to test that results are sorted by name
        runBlocking {
            newsDao.insertAll(listOf(setA, setB))
        }
    }

    @Test fun testGetSets() {
        val list = getValue(newsDao.getNews())
        assertThat(list.size, equalTo(2))

        // Ensure legoSet list is sorted by name
        assertThat(list[0], equalTo(setA))
        assertThat(list[1], equalTo(setB))
    }

}
