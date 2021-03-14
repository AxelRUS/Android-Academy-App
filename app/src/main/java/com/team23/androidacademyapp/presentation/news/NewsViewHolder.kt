package com.team23.androidacademyapp.presentation.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.News

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val content : ImageView = itemView.findViewById(R.id.iv_content)
    private val title : TextView = itemView.findViewById(R.id.tv_title)
    private val description : TextView = itemView.findViewById(R.id.tv_description)

    fun onBind(options: RequestOptions, news: News){
        title.text = news.title
        description.text = news.text

        if(news.content.isEmpty()){
            content.visibility = View.GONE
        }else{
            content.visibility = View.VISIBLE

            Glide.with(context)
                .load(news.content)
                //.apply(options)
                .into(content)
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
