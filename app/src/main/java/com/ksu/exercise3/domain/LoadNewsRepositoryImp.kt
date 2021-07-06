package com.ksu.exercise3.domain

import com.ksu.exercise3.data.dto.ResponseDTO
import com.ksu.exercise3.data.endpoints.NewsEndpoint
import io.reactivex.Single
import retrofit2.Response

class LoadNewsRepositoryImp(private val newsEndpoint: NewsEndpoint) : LoadNewsRepository{

    override fun loadNews(period: String?): Single<Response<ResponseDTO>> {
        return newsEndpoint.period(period)
    }
}