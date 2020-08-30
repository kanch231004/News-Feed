package com.kanchanpal.newsfeed.api

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NewsListResponse(
    @Expose
    @SerializedName("source")
    private var source: String? = null,
    @Expose
    @SerializedName("status")
    private var status: String? = null,
    @Expose
    @SerializedName("articles")
    internal var articles: List<NewsListModel>) : Serializable

@Entity
data class NewsListModel  (

    @PrimaryKey
    @Expose
    @SerializedName("title")
    var title: String = "",
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
    var publishedAt: String? = null,

    @Embedded @SerializedName("source") val source : Source? = null ) : Serializable

data class Source(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String? = ""
)
