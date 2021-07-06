package com.ksu.exercise3.domain

import com.ksu.exercise3.data.dto.NewsDTO

fun NewsDTO.mapNewsDtoToNewsDomain() = NewsDomain(
        url = url!!,
        title = title!!,
        publishedDate = publishedDate!!,
        previewText = previewText!!,
        imageUrl = media?.firstOrNull()?.metaData?.lastOrNull()?.url ?: ""
)