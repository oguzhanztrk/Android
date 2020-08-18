package com.example.myapplication;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static int x;
    private Button button;
    boolean isPaused;
    private ArrayList<String> musicNames;
    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler= new Handler();
    private SeekBar seekBar;

    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private ImageView playPause,nextImage,preImage;
    public  static ImageView random;
    public static Random r;
    public  static MediaPlayer mediaPlayer;
    private TextView textView1,textView2,textView3;
    int u;
    public static int oneTimeOnly = 0;
    int position;
    SharedPreferences mPrefs;

    public ArrayList<String> getMusicNames() {
        return musicNames;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public void mediaTime(TextView textView,double time){

        textView.setText(String.format ("%d min,%d sec",
                TimeUnit.MILLISECONDS.toMinutes((long)time),
                TimeUnit.MILLISECONDS.toSeconds((long)time)- //172-52
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)//toplam saniyeyi dakikaya çeviriyor
                                time))));
    }
    public void startRes(int position){
        u = getResources().getIdentifier(musicNames.get(position), "raw", "com.example.myapplication");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
        finalTime = mediaPlayer.getDuration();
        mediaTime(textView3,finalTime);

        mediaPlayer.start();

    }
   /* public int random(){
        int x;
        r = new Random();
        x = r.nextInt(2);
        return x;
    }*/

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPause = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        nextImage = findViewById(R.id.nextImage);
        preImage = findViewById(R.id.preImage);
       /* random = findViewById(R.id.rand);
        random.setClickable(false);*/

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
/*
        System.out.println(random.isClickable());
*/
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");
        seekBar.setClickable(false);

        musicNames=new ArrayList<String>();
        musicNames.add("neureich");
        musicNames.add("neureichkisa");
        musicNames.add("traviscotthighestinthroom");
      /*  if(seekBar.getProgress()==seekBar.getMax()){  // şarkı bitince buton değiş
            playPause.setImageResource(R.drawable.start);
        }*/
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        playPause.setImageResource(R.drawable.pause);
            startRes(position);

            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();
        //if(oneTimeOnly == 0){
            seekBar.setMax((int)finalTime);
          //  oneTimeOnly = 1;
        //}
        mediaTime(textView3,finalTime);
        mediaTime(textView1,startTime);
        seekBar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime,100);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                    mediaTime(textView1,startTime);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
       /* random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(random.isClickable() == true) {
                    random.setClickable(false);
                    System.out.println(random.isClickable());

                }else
                    random.setClickable(true);
                //System.out.println(random.isClickable());

            }
        });*/
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    finalTime = mediaPlayer.getDuration();
                    startTime = mediaPlayer.getCurrentPosition();


                   // if(oneTimeOnly == 0){
                        seekBar.setMax((int)finalTime);
                     //   oneTimeOnly = 1;
                    //}
                    mediaTime(textView3,finalTime);
                    mediaTime(textView1,startTime);

                    seekBar.setProgress((int) startTime);
                    myHandler.postDelayed(UpdateSongTime,100);
                    playPause.setImageResource(R.drawable.pause);
                }
                else{
                    mediaPlayer.pause();
                    playPause.setImageResource(R.drawable.start);
                }
            }
        });
       nextImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mediaPlayer.stop();
               mediaPlayer.release();
               startRes(position+1);
               position++;

         /*    if(random.isClickable()==true) {

                 Random r = new Random();
                 x = r.nextInt(3);
                 startRes(x);
             }else {
                 //startRes(random());
                 startRes(position + 1);

                 position++;
             }*/
           }
       });
       preImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mediaPlayer.stop();
               mediaPlayer.release();
               if (position > 0){

                   startRes(position-1);
                   position--;

               }
               else {
                   startRes(0);
               }
           }
       });
    }
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            mediaTime(textView1,startTime);

            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}
