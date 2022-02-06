package com.tare.nestedrv.network

import com.tare.nestedrv.pojo.response.ResponseGetNews
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {


    @GET("v2/top-headlines?country=in")
    fun getNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Single<ResponseGetNews>

}