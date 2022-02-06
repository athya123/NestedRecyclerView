package com.tare.nestedrv.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tare.nestedrv.pojo.entities.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){

    init {
        homeRepository.getNewsList()
    }
    val clickedNews = MutableLiveData<Article>()
    val list = homeRepository.newsList

    fun onNewsClick(item: Article){
        clickedNews.postValue(item)
    }

}