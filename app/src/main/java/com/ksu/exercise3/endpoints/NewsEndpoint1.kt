package com.ksu.exercise3.endpoints

import com.ksu.exercise3.dto.ResponseDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsEndpoint {
    @GET("{period}.json")
    fun period(@Path("period") period: String?): Single<Response<ResponseDTO?>?>?
}