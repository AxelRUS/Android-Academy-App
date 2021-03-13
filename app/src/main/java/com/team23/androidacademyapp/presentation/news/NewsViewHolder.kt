package com.team23.androidacademyapp.presentation.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.team23.androidacademyapp.R


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivNews: ImageView = itemView.findViewById(R.id.iv_news)
    private val tvNews: TextView = itemView.findViewById(R.id.tv_news)
    private val tvNewsDescription: TextView = itemView.findViewById(R.id.tv_description)


    fun bind(modelNews: ModelNews) {
        tvNews.text = modelNews.titleNews
        tvNewsDescription.text = modelNews.newsDescription
        Glide.with(itemView.context)
            .load(modelNews.imageNews)
            .into(ivNews)

    }
}