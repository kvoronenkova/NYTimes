package com.ksu.exercise3.adapter

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ksu.exercise3.dto.NewsDTO
import com.ksu.exercise3.dto.ResponseDTO
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class NewsRecyclerAdapter(private val context: Context, private val listener: ClickItemListener, private val glideRequestManager: RequestManager) : RecyclerView.Adapter<TimesViewHolder>() {
    private val items: MutableList<NewsDTO> = ArrayList()
    var onSubscribe: ObservableOnSubscribe<NewsDTO> = ObservableOnSubscribe { emitter ->
        for (i in items.indices) {
            emitter.onNext(items[i]!!)
        }
        emitter.onComplete()
    }
    val observer: Observer<NewsDTO> = object : Observer<NewsDTO> {
        override fun onSubscribe(d: Disposable) {
            Toast.makeText(context, "News", Toast.LENGTH_SHORT).show()
        }

        override fun onNext(newsDTO: NewsDTO) {
            Log.d("Тест123", "Название: " + newsDTO.title)
        }

        override fun onError(e: Throwable) {
            Log.d("Тест123", "Упс ._.")
        }

        override fun onComplete() {
            Log.d("Тест123", "Новости прочитаны")
        }
    }
    var observable = Observable.create(onSubscribe)
            .subscribeOn(Schedulers.io())
            .doOnEach(observer)

    override fun onBindViewHolder(holder: TimesViewHolder, position: Int) {
        val newsItem = items[position]
        holder.bindItem(newsItem, context)
        holder.itemView.setOnClickListener { listener.clickOnItem(newsItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesViewHolder {
        observable.subscribe(observer)
        return TimesViewHolder.Companion.onCreateViewHolder(parent, glideRequestManager)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceItems(items: ResponseDTO) {
        this.items.clear()
        this.items.addAll(items.results?: return)
        notifyDataSetChanged()
    }

    interface ClickItemListener {
        fun clickOnItem(news: NewsDTO?)
    }

    val categoryNews: List<String?>
        get() {
            val categoryList: MutableList<String?> = ArrayList()
            for (i in items.indices) {
                if (!categoryList.contains(items[i].section)) categoryList.add(items[i].section)
            }
            return categoryList
        }
}