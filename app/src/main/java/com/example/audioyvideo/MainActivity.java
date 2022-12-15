package com.example.audioyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6;
    Switch aSwitch;
    MediaPlayer mediaPlayer;
    VideoView videoView;

    @Override
    protected void onStop() {
        super.onStop();

        if(mediaPlayer!=null){
            mediaPlayer.release();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        aSwitch=findViewById(R.id.switch1);
        videoView=findViewById(R.id.videoView);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music);
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(),"Iniciar",Toast.LENGTH_LONG).show();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    Toast.makeText(getApplicationContext(),"Pausar",Toast.LENGTH_LONG).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null && !mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"Continuar",Toast.LENGTH_LONG).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null){
                    int posicion = mediaPlayer.getCurrentPosition();
                    int posicionNueva=posicion+5000;
                    mediaPlayer.seekTo(posicionNueva);
                }
            }
        });

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aSwitch.isChecked()){
                    mediaPlayer.setLooping(true);
                }else{
                    mediaPlayer.setLooping(false);
                }
            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));

                videoView.start();
            }
        });

    }
}