package com.kanchanpal.newsfeed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kanchanpal.newsfeed.data.newsSet.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class NewsViewModelTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = mock(NewsRepository::class.java)

    @Test
    fun testNull() {

        verify(repository, never()).observePagedNews(false, coroutineScope)
        verify(repository, never()).observePagedNews(true, coroutineScope)
    }

    @Test
    fun doNotFetchWithoutObservers() {

        verify(repository, never()).observePagedNews(false, coroutineScope)
    }

    @Test
    fun doNotFetchWithoutObserverOnConnectionChange() {

        verify(repository, never()).observePagedNews(true, coroutineScope)
    }

}
