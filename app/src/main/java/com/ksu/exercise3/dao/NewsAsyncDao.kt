package com.ksu.exercise3.dao

import android.database.Observable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ksu.exercise3.entity.NewsEntity


@Dao
interface NewsAsyncDao {

    @Query("SELECT * FROM news")
    fun getAll(): Observable<List<NewsEntity?>?>?

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg news: NewsEntity)

    @Delete
    fun delete(film: NewsEntity?)

    @Query("DELETE FROM news")
    fun deleteAll()
}