package com.ksu.exercise3.helpers

import android.content.Context
import com.ksu.exercise3.entity.NewsEntity
import io.reactivex.Completable
import java.util.concurrent.Callable


class NewsRepository(private var context: Context) {
    fun saveData(newList: List<NewsEntity?>): Completable? {
        return Completable.fromCallable(object : Callable<Void?> {
            @Throws(Exception::class)
            override fun call(): Void? {
                val db: AppDatabase? = AppDatabase.getAppDatabase(context)

                //db.filmDao().deleteAll();
                val news: List<NewsEntity> = newList.filterNotNull()
                db?.newsDao()?.insertAll(*news.toTypedArray())
                return null
            }
        })
    }
}