package com.ksu.exercise3.dto

import com.google.gson.annotations.SerializedName

data class ResponseDTO (
    @SerializedName("results")
    val results: List<NewsDTO>? = null
)