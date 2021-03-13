package com.team23.androidacademyapp.presentation.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.team23.androidacademyapp.R


class NewsAdapter() : ListAdapter<ModelNews, NewsViewHolder>(DiffCallBackNews()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val modelNews = getItem(position)
        holder.bind(modelNews)
    }
}

class DiffCallBackNews : DiffUtil.ItemCallback<ModelNews>() {
    override fun areItemsTheSame(oldItem: ModelNews, newItem: ModelNews): Boolean {
        return oldItem.titleNews == newItem.titleNews
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ModelNews, newItem: ModelNews): Boolean {
        return oldItem == newItem
    }
}