package com.tare.nestedrv.pojo.response


import com.google.gson.annotations.SerializedName
import com.tare.nestedrv.pojo.entities.Article

data class ResponseGetNews(
    @SerializedName("articles")
    var articles: List<Article> = listOf(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: Int = 0
)