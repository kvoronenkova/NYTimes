package com.ksu.exercise3.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ksu.exercise3.R
import com.ksu.exercise3.domain.NewsDomain
import kotlinx.android.synthetic.main.item_news.view.*

class TimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(newsDomain: NewsDomain, requestManager: RequestManager) {

        with (itemView){
            nameNews.text = newsDomain.title
            textNews.text = newsDomain.previewText
            dateNews.text = newsDomain.publishedDate
            requestManager.applyDefaultRequestOptions(RequestOptions().centerCrop()).load(newsDomain.imageUrl).into(imageItem)
        }
    }

    companion object {
        private const val LAYOUT = R.layout.item_news
        fun onCreateViewHolder(parent: ViewGroup): TimesViewHolder {
            return TimesViewHolder(
                    LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false))
        }
    }

}