package com.team23.androidacademyapp.presentation.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.team23.androidacademyapp.R

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val content : ImageView = itemView.findViewById(R.id.iv_content)
    private val title : TextView = itemView.findViewById(R.id.tv_title)

    fun onBind(options: RequestOptions, news: ModelNews){
        title.text = news.title

        Glide.with(context)
            .load(news.content)
            //.apply(options)
            .into(content)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
