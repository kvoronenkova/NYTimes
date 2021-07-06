package com.ksu.exercise3.domain

import com.ksu.exercise3.data.dto.ResponseDTO
import io.reactivex.Single
import retrofit2.Response

interface LoadNewsRepository {
    fun loadNews(period: String?) : Single<Response<ResponseDTO>>
}