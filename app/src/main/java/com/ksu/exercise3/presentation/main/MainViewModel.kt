package com.ksu.exercise3.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksu.exercise3.domain.GetNewsUseCase
import com.ksu.exercise3.domain.LoadNewsRepositoryImp
import com.ksu.exercise3.domain.NewsDomain
import com.ksu.exercise3.network.Controller


class MainViewModel() : ViewModel() {
    val liveDataNews = MutableLiveData<List<NewsDomain>>()
    val progressLiveData = MutableLiveData<Boolean>(true)

    private val controller = Controller.instance


    private val loadNewsRepositoryImp = LoadNewsRepositoryImp(controller.news())
    private val getNewsUseCase: GetNewsUseCase = GetNewsUseCase(loadNewsRepositoryImp)

    init {
        loadNews("7")
    }

    private fun loadNews(period: String?) {
        if (period == null) return
        getNewsUseCase.savePeriodNum(period)
        getNewsUseCase.execute(
                onSuccess = {
                    liveDataNews.value = it
                },
                onError = {
                    it.printStackTrace()
                }
        )
    }

}
