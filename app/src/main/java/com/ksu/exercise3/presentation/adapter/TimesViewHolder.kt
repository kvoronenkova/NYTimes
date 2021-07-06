package com.ksu.exercise3.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ksu.exercise3.R
import com.ksu.exercise3.domain.NewsDomain

class TimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.image_item)
    private val nameNewsView: TextView = itemView.findViewById(R.id.name_news)
    private val textNewsView: TextView = itemView.findViewById(R.id.text_news)
    private val dateNewsView: TextView = itemView.findViewById(R.id.date_news)

    fun bindItem(newsDomain: NewsDomain, requestManager: RequestManager) {
        nameNewsView.text = newsDomain.title
        textNewsView.text = newsDomain.previewText
        dateNewsView.text = newsDomain.publishedDate
        requestManager.applyDefaultRequestOptions(RequestOptions().centerCrop()).load(newsDomain.imageUrl).into(imageView)
    }

    companion object {
        private const val LAYOUT = R.layout.item_news
        fun onCreateViewHolder(parent: ViewGroup): TimesViewHolder {
            return TimesViewHolder(
                    LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false))
        }
    }

}