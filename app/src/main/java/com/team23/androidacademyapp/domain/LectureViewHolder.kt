package com.team23.androidacademyapp.domain

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Model

class LectureViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

    private val youTubePlayerView:YouTubePlayerView=itemView.findViewById(R.id.youtube_pleer)
    private val tvTitle:TextView = itemView.findViewById(R.id.iv_content)
    private val tvDescription:TextView = itemView.findViewById(R.id.tv_description)

    fun bind(model: Model){
        tvTitle.text=model.title
        tvDescription.text=model.description
       // youTubePlayerView.loadVideo(model.video)
    }

}
