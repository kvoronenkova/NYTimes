package com.ksu.exercise3.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ksu.exercise3.domain.NewsDomain
import java.util.*

class NewsRecyclerAdapter(private val requestManager: RequestManager, private val listener: ClickItemListener) : RecyclerView.Adapter<TimesViewHolder>() {
    private val items: MutableList<NewsDomain> = ArrayList()

    override fun onBindViewHolder(holder: TimesViewHolder, position: Int) {
        val newsItem = items[position]
        holder.bindItem(newsItem, requestManager)
        holder.itemView.setOnClickListener { listener.clickOnItem(newsItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesViewHolder {
        return TimesViewHolder.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceItems(items: List<NewsDomain>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface ClickItemListener {
        fun clickOnItem(news: NewsDomain)
    }
}