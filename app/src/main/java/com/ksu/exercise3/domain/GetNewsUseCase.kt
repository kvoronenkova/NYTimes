package com.ksu.exercise3.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GetNewsUseCase(private val repository: LoadNewsRepositoryImp) {

    private var periodNum: String? = null
    private val compositeDisposable = CompositeDisposable()

    fun savePeriodNum(period: String) {
        periodNum = period
    }

    fun execute(
            onSuccess: ((t: List<NewsDomain>) -> Unit),
            onError: ((t: Throwable) -> Unit),
            onFinished: () -> Unit = {}
    ) {
        val searchDisposable = repository.loadNews(periodNum)
                .map { it.body()?.results?.map { newsDTO -> newsDTO.mapNewsDtoToNewsDomain() } ?: emptyList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(onFinished)
                .subscribe(onSuccess, onError)
        searchDisposable.let {
            compositeDisposable.add(searchDisposable)
        }
    }
}