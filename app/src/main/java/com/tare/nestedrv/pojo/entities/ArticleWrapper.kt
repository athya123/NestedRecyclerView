package com.tare.nestedrv.pojo.entities

data class ArticleWrapper(
    var category: String,
    var newsList: List<Article>,
)
