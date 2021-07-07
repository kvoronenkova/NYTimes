package com.ksu.exercise3.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksu.exercise3.domain.GetNewsUseCase
import com.ksu.exercise3.domain.NewsDomain


class MainViewModel (private val useCase: GetNewsUseCase ) : ViewModel() {
    val liveDataNews = MutableLiveData<List<NewsDomain>>()

    init {
        loadNews("7")
    }

    fun loadNews(period: String?) {
        if (period == null) return
        useCase.savePeriodNum(period)
        useCase.execute(
                onSuccess = {
                    liveDataNews.value = it
                },
                onError = {
                    it.printStackTrace()
                }
        )
    }

}
