package com.ksu.exercise3.helpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ksu.exercise3.dao.NewsAsyncDao
import com.ksu.exercise3.dao.NewsDao
import com.ksu.exercise3.entity.NewsEntity


@Database(entities = arrayOf(NewsEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {



    abstract fun newsDao(): NewsDao?

    abstract fun newsAsyncDao(): NewsAsyncDao?

    companion object {
        private var singleton: AppDatabase? = null
        private val DATABASE_NAME = "NewsRoomDb.db"

        fun getAppDatabase(context: Context): AppDatabase? {

            if (singleton == null) {
                synchronized(AppDatabase::class.java) {
                    if (singleton == null) {
                        singleton = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase::class.java,
                                DATABASE_NAME)
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return singleton
        }
    }
}