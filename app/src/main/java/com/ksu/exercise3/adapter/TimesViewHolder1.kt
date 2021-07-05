package com.ksu.exercise3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ksu.exercise3.R
import com.ksu.exercise3.dto.NewsDTO

class TimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView
    private val nameNewsView: TextView
    private val textNewsView: TextView
    private val dateNewsView: TextView

    fun bindItem(newsDTO: NewsDTO, context: Context?) {
        nameNewsView.text = newsDTO.title
        textNewsView.text = newsDTO.previewText
        dateNewsView.text = newsDTO.published_date
        if (newsDTO.media?.size != 0) Glide.with(context!!).applyDefaultRequestOptions(RequestOptions().centerCrop()).load(newsDTO.media?.get(0)?.metaData?.get(2)?.url).into(imageView)
    }

    companion object {
        private const val LAYOUT = R.layout.item_news
        fun onCreateViewHolder(parent: ViewGroup): TimesViewHolder {
            return TimesViewHolder(
                    LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false))
        }
    }

    //final Observable<NewsDTO> observable = Observable.
    init {
        imageView = itemView.findViewById(R.id.image_item)
        nameNewsView = itemView.findViewById(R.id.name_news)
        textNewsView = itemView.findViewById(R.id.text_news)
        dateNewsView = itemView.findViewById(R.id.date_news)
    }
}