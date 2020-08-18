package com.example.dene

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mediaPlayer: MediaPlayer
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.deniz_sesi)
        mediaPlayer.setLooping(true)

        mediaPlayer.start()
    }

}
