package com.team23.androidacademyapp.presentation.title

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Model

class LectureViewHolder (
    itemView: View,
    private val onQuizeClickListener : OnQuizClickListener,
    private val onWorkshopClickListener: OnWorkshopClickListener,
    private val onFeedbackClickListener: OnFeedbackClickListener
):RecyclerView.ViewHolder(itemView){

    private val youTubePlayerView:YouTubePlayerView=itemView.findViewById(R.id.youtube_pleer)
    private val tvTitle:TextView = itemView.findViewById(R.id.iv_content)
    private val tvDescription:TextView = itemView.findViewById(R.id.tv_description)
    private val ivQuiz:ImageView = itemView.findViewById(R.id.iv_quiz)
    private val ivWorkshop:ImageView = itemView.findViewById(R.id.iv_workshop)
    private val ivFeedback:ImageView = itemView.findViewById(R.id.iv_feedback)

    private val tvQuizLabel:TextView = itemView.findViewById(R.id.tv_label_quiz)
    private val tvWorkshopLabel:TextView = itemView.findViewById(R.id.tv_label_workshop)
    private val tvFeedbacLabel:TextView = itemView.findViewById(R.id.tv_label_feedback)


    fun bind(model: Model){
        tvTitle.text=model.title
        tvDescription.text=model.description

       // lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                 youTubePlayer.cueVideo(model.video, 0f)
            }
        })

        if (model.wokrshop.isEmpty()){
            ivWorkshop.visibility = View.INVISIBLE
            tvWorkshopLabel.visibility = View.INVISIBLE
        }else{
            ivWorkshop.visibility = View.VISIBLE
            tvWorkshopLabel.visibility = View.VISIBLE
        }

        if (model.feedback.isEmpty()){
            ivFeedback.visibility = View.INVISIBLE
            tvFeedbacLabel.visibility = View.INVISIBLE
        }else{
            ivFeedback.visibility = View.VISIBLE
            tvFeedbacLabel.visibility = View.VISIBLE
        }

        ivQuiz.setOnClickListener{
            onQuizeClickListener.onClick(model)
        }

        ivWorkshop.setOnClickListener{
            onWorkshopClickListener.onClick(model)
        }

        ivFeedback.setOnClickListener{
            onFeedbackClickListener.onClick(model)
        }
    }

}
