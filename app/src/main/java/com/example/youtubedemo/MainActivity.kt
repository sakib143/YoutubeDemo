package com.example.youtubedemo

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        youtube_player_view.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = extractYoutubeVideoId("https://www.youtube.com/watch?v=45QLMt0-yPw&feature=youtu.be")
                if (videoId!=null) {
                    youTubePlayer.play()
                    youTubePlayer.loadVideo(videoId!!, 0f)
                }
                else
                    Toast.makeText(this@MainActivity,"Not Get Proper Url",Toast.LENGTH_SHORT).show()
            }
        })
        youtube_player_view.enterFullScreen()
    }

    fun extractYoutubeVideoId(ytUrl: String?): String? {
        var vId: String? = null
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(ytUrl)
        if (matcher.find()) {
            vId = matcher.group()
        }
        return vId
    }

}