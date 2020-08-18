package com.example.whitenoiseedit

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.view.View

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.whitenoiseedit.Services.NotificationActionService
import com.example.whitenoise.Track

class CreateNotification {
    companion object{
        val Channel_ID = "channel1"
        val ACTION_PREVIOUS = "actionprevious"
        val ACTION_PLAY = "actionplay"
        val ACTION_NEXT = "actionnext"

        lateinit var notification:Notification

        fun createdNotification(context: Context, track: Track, playbutton:Int, pos:Int, size:Int){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                var notificationManagerCompat:NotificationManagerCompat = NotificationManagerCompat.from(context)
                var mediaSessionCompat: MediaSessionCompat = MediaSessionCompat(context,"tag")

                var icon: Bitmap? = BitmapFactory.decodeResource(context.getResources(),track.image)

                var pendingIntentPrevious: PendingIntent?
                var drw_previous:Int
                if(pos == 0){
                    pendingIntentPrevious = null
                    drw_previous = 0
                }else {
                    var intentPrevious = Intent(context, NotificationActionService::class.java)
                        .setAction(ACTION_PREVIOUS)
                    pendingIntentPrevious = PendingIntent.getBroadcast(context,0,intentPrevious,PendingIntent.FLAG_UPDATE_CURRENT)
                    drw_previous = R.drawable.ic_pause_black_24dp
                }

                var intentPlay = Intent(context, NotificationActionService::class.java)
                    .setAction(ACTION_PLAY)
                var pendingIntentPlay:PendingIntent = PendingIntent.getBroadcast(context,0,
                    intentPlay,PendingIntent.FLAG_UPDATE_CURRENT)




                var notification = NotificationCompat.Builder(context, Channel_ID)
                    .setSmallIcon(R.drawable.ic_music_note)
                    .setContentTitle(track.title)
                    .setContentText(track.artist)
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .addAction(playbutton,"Play",pendingIntentPlay)
                    .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build()

                notificationManagerCompat.notify(1,notification)
            }

        }
    }

}