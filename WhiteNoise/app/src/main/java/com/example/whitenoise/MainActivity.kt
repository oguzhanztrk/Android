package com.example.whitenoise

import android.app.NotificationChannel
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.whitenoise.Services.OnClearFromRecentService
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.app.NotificationManager as NotificationManager

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    var is_Playing = false
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer

    //lateinit var broadcastsdda: BroadcastReceiver

    lateinit var notificationManager: NotificationManager
    lateinit var tracks:Track
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tracks=Track("white noise","noise",R.drawable.uzungol)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            registerReceiver(broadcastReciever, IntentFilter("TRACKS_TRACKS"))
            startService( Intent(baseContext,OnClearFromRecentService::class.java))
        }


        //var mediaPlayer: MediaPlayer
        //var mediaPlayer2: MediaPlayer
        var audioManager: AudioManager

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.cekirge)
        mediaPlayer2 = MediaPlayer.create(applicationContext, R.raw.deniz_sesi)

        audioManager =
            getApplicationContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        seekBar1.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
        seekBar1.setProgress(audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC))

        seekbar(seekBar1, "deniz_sesi", audioManager)
        seekbar(seekBar2, "cekirge", audioManager)


        mediaPlayer.setLooping(true)
        mediaPlayer2.setLooping(true)


        play.setOnClickListener {
            //CreateNotification.createdNotification(this, tracks, R.drawable.ic_pause_black_24dp, 1, 1)
            if (!mediaPlayer.isPlaying) {
                onTrackPlay()
                mediaPlayer.start()
                mediaPlayer2.start()


               //play.setImageResource(R.drawable.ic_play_arrow_black_24dp)

            } else {
                onTrackPause()
                mediaPlayer.pause()
                mediaPlayer2.pause()




                //play.setImageResource(R.drawable.ic_pause_black_24dp)
            }

        }

    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                CreateNotification.Channel_ID,
                "Kod DEV", NotificationManager.IMPORTANCE_LOW)


            notificationManager = getSystemService(NotificationManager::class.java)
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel)
            }

        }
    }
    private var broadcastReciever = object:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var action = intent?.getExtras()?.getString("actionname")

            when (action) {
                CreateNotification.ACTION_PLAY ->
                    if(is_Playing){
                        mediaPlayer.pause()
                        mediaPlayer2.pause()

                        onTrackPause()

                    }else{
                        mediaPlayer.start()
                        mediaPlayer2.start()
                        onTrackPlay()


                    }
            }
        }
    }

    fun onTrackPlay() {
        CreateNotification.createdNotification(this,tracks,R.drawable.ic_pause_black_24dp,0,1)
        play.setImageResource(R.drawable.ic_pause_black_24dp)
        //title.setText(track.title)
        is_Playing = true
        //mediaPlayer.start()
        //mediaPlayer2.start()
    }

    fun onTrackPause() {
        CreateNotification.createdNotification(this,tracks,R.drawable.ic_play_arrow_black_24dp,1,1)
        play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        //title.setText(track.title)
        is_Playing = false
        //mediaPlayer.pause()
        //mediaPlayer2.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll()
        }
        unregisterReceiver(broadcastReciever)
    }

    fun seekbar(seekBar: SeekBar, musicName: String, audioManager: AudioManager) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var u = resources.getIdentifier(
                musicName,
                "raw",
                "com.example.myapplication"
            )

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                //Toast.makeText(applicationContext, "seekbar progress: $progress", Toast.LENGTH_SHORT).show()
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    progress,
                    u
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
