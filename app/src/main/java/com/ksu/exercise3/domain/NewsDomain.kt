package com.ksu.exercise3.domain

data class NewsDomain(
        val url: String,
        val publishedDate: String,
        val title: String,
        val previewText: String,
        val imageUrl: String
        )