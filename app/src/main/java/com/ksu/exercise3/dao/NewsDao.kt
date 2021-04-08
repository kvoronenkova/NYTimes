package com.ksu.exercise3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ksu.exercise3.entity.NewsEntity


@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity?>?

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNewsById(id: Int): NewsEntity?

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg news: NewsEntity?)

    @Insert(onConflict = REPLACE)
    fun insert(film: NewsEntity?)

    @Delete
    fun delete(film: NewsEntity?)

    @Query("DELETE FROM news")
    fun deleteAll()
}