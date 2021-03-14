package com.team23.androidacademyapp.presentation.mentors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Mentor


class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.iv_avatar)
    private val name: TextView = itemView.findViewById(R.id.tv_name)

    fun onBind(options: RequestOptions, mentor: Mentor) {
        name.text = mentor.surname + " " + mentor.name

        Glide.with(context)
            .load(mentor.foto)
            .apply(options)
            .into(avatar)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(mentor: Mentor)
}