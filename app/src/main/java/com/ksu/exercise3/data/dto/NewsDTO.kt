package com.ksu.exercise3.data.dto

import com.google.gson.annotations.SerializedName

class NewsDTO(
        @SerializedName("url")
        val url: String,

        @SerializedName("media")
        val media: List<Media>? = null,

        @SerializedName("published_date")
        val publishedDate: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("abstract")
        val previewText: String,

        @SerializedName("section")
        val section: String
)