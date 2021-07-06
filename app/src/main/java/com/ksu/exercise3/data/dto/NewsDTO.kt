package com.ksu.exercise3.data.dto

import com.google.gson.annotations.SerializedName

class NewsDTO {
    @SerializedName("url")
    val url: String? = null

    @SerializedName("published_date")
    val publishedDate: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("abstract")
    val previewText: String? = null

    @SerializedName("section")
    val section: String? = null

    @SerializedName("media")
    val media: List<Media>? = null
}