package com.cnx.newsfeed.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class NewsListResponse(
    @Expose
    @SerializedName("source")
    private var source: String? = null,
    @Expose
    @SerializedName("status")
    private var status: String? = null,
    @Expose
    @SerializedName("sortBy")
    private var sortBy: String? = null,
    @Expose
    @SerializedName("articles")
    internal var articles: List<NewsListModel>)



@Entity
data class NewsListModel  (

    @PrimaryKey(autoGenerate = true)  var id : Int,
    @Expose
    @SerializedName("title")
    var title: String? = null,
    @Expose
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @Expose
    @SerializedName("description")
    var description: String? = null,
    @Expose
    @SerializedName("author")
    var author: String? = null,
    @Expose
    @SerializedName("url")
    var url: String? = null,
    @Expose
    @SerializedName("publishedAt")
    var publishedAt: String? = null)