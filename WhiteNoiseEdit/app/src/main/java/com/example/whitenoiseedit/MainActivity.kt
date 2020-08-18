package com.example.whitenoiseedit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.SeekBar
import com.example.whitenoise.Track
import com.example.whitenoiseedit.Services.OnClearFromRecentService
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    var is_Playing = false

    lateinit var mediaPlayer1:MediaPlayer
    lateinit var broadcastsdda: BroadcastReceiver

    lateinit var notificationManager: NotificationManager
    lateinit var tracks: Track
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tracks=Track("white noise","noise",R.drawable.uzungol)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            registerReceiver(broadcastReciever, IntentFilter("TRACKS_TRACKS"))
            startService( Intent(baseContext, OnClearFromRecentService::class.java))
        }

            invisible()




        var audioManager: AudioManager
        mediaPlayer1 = MediaPlayer.create(applicationContext, R.raw.deniz_sesi)

        audioManager =
            getApplicationContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        seekBar1.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
        seekBar1.setProgress(audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC))

        seekbar(seekBar1, "deniz_sesi", audioManager)
        mediaPlayer1.setLooping(true)


        OnClickedImageView()

    }

    private fun invisible() {
        seekBar1.setVisibility(View.INVISIBLE)
        seekBar2.setVisibility(View.INVISIBLE)
        seekBar3.setVisibility(View.INVISIBLE)
        seekBar4.setVisibility(View.INVISIBLE)
        seekBar5.setVisibility(View.INVISIBLE)
        seekBar6.setVisibility(View.INVISIBLE)
        seekBar7.setVisibility(View.INVISIBLE)
        seekBar8.setVisibility(View.INVISIBLE)
        seekBar9.setVisibility(View.INVISIBLE)
        seekBar10.setVisibility(View.INVISIBLE)
        seekBar11.setVisibility(View.INVISIBLE)
        seekBar12.setVisibility(View.INVISIBLE)
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
    fun onClickImageView(mediaPlayer:MediaPlayer,seekBar: SeekBar,imageView: View){
        imageView.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                onTrackPlay()
                mediaPlayer.start()

                seekBar.setVisibility(View.VISIBLE)
            } else {
                onTrackPause()
                mediaPlayer.pause()


                seekBar.setVisibility(View.INVISIBLE)

            }
        }
    }
    fun OnClickedImageView(){
        onClickImageView(mediaPlayer1,seekBar1,imageView1)
        onClickImageView(mediaPlayer1,seekBar2,imageView2)
        onClickImageView(mediaPlayer1,seekBar3,imageView3)
        onClickImageView(mediaPlayer1,seekBar4,imageView4)
        onClickImageView(mediaPlayer1,seekBar5,imageView5)
        onClickImageView(mediaPlayer1,seekBar6,imageView6)
        onClickImageView(mediaPlayer1,seekBar7,imageView7)
        onClickImageView(mediaPlayer1,seekBar8,imageView8)
        onClickImageView(mediaPlayer1,seekBar9,imageView9)
        onClickImageView(mediaPlayer1,seekBar10,imageView10)
        onClickImageView(mediaPlayer1,seekBar11,imageView11)
        onClickImageView(mediaPlayer1,seekBar12,imageView12)

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
    var broadcastReciever:BroadcastReceiver= object:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var action = intent?.getExtras()?.getString("actionname")

            when (action) {
                CreateNotification.ACTION_PLAY ->
                    if(mediaPlayer1.isPlaying){
                        mediaPlayer1.pause()
                        onTrackPause()

                    }else{
                        mediaPlayer1.start()
                        onTrackPlay()
                    }
            }
        }
    }

    fun onTrackPlay() {
        CreateNotification.createdNotification(this,tracks,R.drawable.ic_pause_black_24dp,1,1)
        //play.setImageResource(R.drawable.ic_pause_black_24dp)
        //title.setText(track.title)
        is_Playing = true
        //mediaPlayer.start()
        //mediaPlayer2.start()
    }

    fun onTrackPause() {
        CreateNotification.createdNotification(this,tracks,R.drawable.ic_play_arrow_black_24dp,1,1)
        //play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
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





}
