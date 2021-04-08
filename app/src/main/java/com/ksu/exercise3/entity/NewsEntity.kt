package com.ksu.exercise3.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "news")
class NewsEntity(

    @PrimaryKey
    private val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "published_d")
    val publishedDate: Date,

    @ColumnInfo(name = "category")
    val nameCategory: String,

    @ColumnInfo(name = "preview")
    val previewText: String
)




