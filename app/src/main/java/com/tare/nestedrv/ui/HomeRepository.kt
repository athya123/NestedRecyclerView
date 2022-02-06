package com.tare.nestedrv.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tare.nestedrv.network.Services
import com.tare.nestedrv.pojo.entities.ArticleWrapper
import com.tare.nestedrv.pojo.response.ResponseGetNews
import com.tare.nestedrv.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository
@Inject constructor(
    private val webServices: Services,
    private val constants: Constants
) {

    private val _newsList = MutableLiveData<List<ArticleWrapper>>()
    val newsList: LiveData<List<ArticleWrapper>>
        get() = _newsList

    fun getNewsList() {
        Single.zip(
            webServices.getNews("business", constants.APIKEY),
            webServices.getNews("entertainment", constants.APIKEY),
            webServices.getNews("general", constants.APIKEY),
            webServices.getNews("health", constants.APIKEY),
            webServices.getNews("science", constants.APIKEY),
            webServices.getNews("sports", constants.APIKEY),
            webServices.getNews("technology", constants.APIKEY)
        ) { business, entertainment, general, health, science, sports, technology ->
            createList(business, entertainment, general, health, science, sports, technology)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _newsList.postValue(it)
            },
                {
                    Log.e("HOMEREPO", "ERROR", it)
                })
    }

    private fun createList(
        business: ResponseGetNews,
        entertainment: ResponseGetNews,
        general: ResponseGetNews,
        health: ResponseGetNews,
        science: ResponseGetNews,
        sports: ResponseGetNews,
        technology: ResponseGetNews
    ): List<ArticleWrapper> {
        val ans = mutableListOf<ArticleWrapper>()

        ans.add(ArticleWrapper("Business", business.articles))
        ans.add(ArticleWrapper("Entertainment", entertainment.articles))
        ans.add(ArticleWrapper("General", general.articles))
        ans.add(ArticleWrapper("Health", health.articles))
        ans.add(ArticleWrapper("Science", science.articles))
        ans.add(ArticleWrapper("Sports", sports.articles))
        ans.add(ArticleWrapper("Technology", technology.articles))

        return ans
    }

}