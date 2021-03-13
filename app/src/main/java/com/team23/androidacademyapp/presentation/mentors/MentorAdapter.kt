package com.team23.androidacademyapp.presentation.mentors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.ModelMentor

class MentorAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<MentorViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var mentors: List<ModelMentor> = listOf<ModelMentor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        return MentorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.ment_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.onBind(imageOption, mentors.get(position))
        holder.itemView.setOnClickListener {
            clickListener.onClick(mentors.get(position))
        }
    }

    override fun getItemCount(): Int {
        return mentors.size
    }

    fun bindMentors(newMentors: List<ModelMentor>) {
        mentors = newMentors
    }

}
