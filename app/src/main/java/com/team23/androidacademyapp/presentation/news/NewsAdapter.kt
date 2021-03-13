package com.team23.androidacademyapp.presentation.news

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.ModelNews

class NewsAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<NewsViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var news: List<ModelNews> = listOf<ModelNews>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(imageOption, news[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(news[position])
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun bindNews(newNews: List<ModelNews>) {
        news = newNews
    }
}

interface OnRecyclerItemClicked {
    fun onClick(news: ModelNews)
}