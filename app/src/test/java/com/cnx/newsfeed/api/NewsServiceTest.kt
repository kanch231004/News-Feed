package com.cnx.newsfeed.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class NewsServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: NewsService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestLegoSets() {
        runBlocking {
            enqueueResponse("news.json")
            val resultResponse = service.getTopNewsList().body()

            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            assertThat(request.path, `is`("/v2/everything"))
        }
    }

    @Test
    fun getLegoSetsResponse() {
        runBlocking {
            enqueueResponse("news.json")
            val resultResponse = service.getTopNewsList().body()
            val legoSets = resultResponse!!.articles

            assertThat(resultResponse.articles.size, `is`(10))
            assertThat(legoSets.size, `is`(10))
        }
    }

    /* @Test
     fun getLegoSetsPagination() {
         runBlocking {
             enqueueResponse("news.json")
             val resultResponse = service.getTopNewsList().body()

             assertNull(resultResponse!!.)
             assertNull(resultResponse.previous)
         }
     }*/


    /*  @Test
      fun getLegoSetItem() {
          runBlocking {
              enqueueResponse("news.json")
              val resultResponse = service.getTopNewsList().body()
              val legoSets = resultResponse!!.articles

              val legoSet = legoSets[0]
              assertThat(legoSet.title, `is`("Man Claims He Invented Bitcoin, Is Ordered to Pay Billions in Bitcoin"))
              assertThat(legoSet.description, `is`("A man who has insisted he is the man behind the pseudonymous identity of Satoshi Nakamoto, inventor of bitcoin, has been ordered to pay half of his cryptocurrency bounty to a man believed to be his former colleague. Read more..."))
              assertThat(legoSet.publishedAt, `is`("2019-08-28T16:50:00Z"))
              assertThat(legoSet.source.name, `is`("Gizmodo.com"))
              assertThat(legoSet.urlToImage, `is`("https://i.kinja-img.com/gawker-media/image/upload/s--H8pqYMUW--/c_fill,fl_progressive,g_center,h_900,q_80,w_1600/ug34lxszlekl8efydtj3.png"))
          }
      }*/

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }
}


