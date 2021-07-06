package com.ksu.exercise3.data.dto

import com.google.gson.annotations.SerializedName

class Media {
    @SerializedName("media-metadata")
    val metaData: List<MediaMetaData>? = null
}