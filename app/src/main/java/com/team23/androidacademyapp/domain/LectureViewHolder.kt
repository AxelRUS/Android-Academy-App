package com.team23.androidacademyapp.domain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.team23.androidacademyapp.R

class LectureViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

    private val youTubePlayerView:YouTubePlayerView=itemView.findViewById(R.id.youtube_pleer)

    fun bind(model: Model){
     // youTubePlayerView
    }

}
