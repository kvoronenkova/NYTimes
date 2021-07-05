package com.ksu.exercise3.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksu.exercise3.dto.NewsDTO
import com.ksu.exercise3.dto.ResponseDTO
import com.ksu.exercise3.network.Controller
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException


class MainViewModel: ViewModel(){
    val liveDataNews = MutableLiveData<Response<ResponseDTO>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        loadNews("7")
    }

    private fun loadNews(period: String) {
        val searchDisposable = Controller.instance
                ?.news()
                ?.period(period)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response: Response<ResponseDTO> -> liveDataNews.postValue(response) }) { throwable: Throwable -> handleError(throwable) }
        if (searchDisposable != null) {
            compositeDisposable.add(searchDisposable)
        }
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        if (throwable is IOException) {
            return
        }
    }
}
