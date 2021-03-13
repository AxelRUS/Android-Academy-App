package com.team23.androidacademyapp.presentation.title

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Model


class LectureDetailFragment : AppCompatActivity() {

    private lateinit var viewModel: LectureDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lecture_detail_fragment)

        val model = intent.extras?.getParcelable<Model>("lecture")!!
        //viewModel = ViewModelProvider(this).get(LectureDetailViewModel::class.java)

        val title = findViewById<TextView>(R.id.tv_title)
        val description = findViewById<TextView>(R.id.tv_description)
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_pleer)

        title.text = model?.title
        description.text = model?.description

        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "S0Q4gqBUs7c"
                youTubePlayer.loadVideo(model.video, 0f)
            }
        })
       // youTubePlayerView.vi(model?.video)

    }

}