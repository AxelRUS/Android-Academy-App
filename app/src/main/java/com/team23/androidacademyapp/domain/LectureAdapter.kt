package com.team23.androidacademyapp.domain


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Model
import com.team23.androidacademyapp.presentation.title.OnFeedbackClickListener
import com.team23.androidacademyapp.presentation.title.OnQuizClickListener
import com.team23.androidacademyapp.presentation.title.OnWorkshopClickListener


class LectureAdapter(
    private val onQuizClickListener : OnQuizClickListener,
    private val onWorkshopClickListener: OnWorkshopClickListener,
    private val onFeedbackClickListener: OnFeedbackClickListener
) : ListAdapter<Model, LectureViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder =
        LectureViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lecture, parent, false),
            onQuizClickListener,
            onWorkshopClickListener,
            onFeedbackClickListener
        )

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.video == newItem.video
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem == newItem
    }
}