package com.example.audioyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
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
        //Al salir de la aplicación, si existe un objeto mediaPlayer lo borramos
        if(mediaPlayer!=null){
            //Lo cerramos liberando los recursos asociados
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

        //Enlazamos la vista con el recurso de vídeo
        videoView.setVideoURI(Uri.parse("https://www.scratchya.com.ar/video1.mp4"));

        //Creamos el objeto que se ocupara de controlar la reproducción
        MediaController mediaController=new MediaController(getApplicationContext());
        //Lo asociamos al videoView
        videoView.setMediaController(mediaController);
        //Fijamos el controlar del vídeo a la vista del vídeo
        mediaController.setAnchorView(videoView);

        videoView.start();


        //Botón para comenzar la reproducción
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music);
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(),"Iniciar",Toast.LENGTH_LONG).show();
            }
        });

        //Botón para pausar la reproducción
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sólo se podrá parar la reproducción si ésta estaba en funcionamiento (el mediaPlayer existe y se está reproduciendo)
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    Toast.makeText(getApplicationContext(),"Pausar",Toast.LENGTH_LONG).show();
                }
            }
        });

        //Botón para reanudar la reproducción
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sólo se podrá reanudar la reproducción si la reproducción estaba parada (el mediaPlayer existe y no se está reproduciendo)
                if(mediaPlayer!=null && !mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"Continuar",Toast.LENGTH_LONG).show();
                }
            }
        });

        //Botón para avanzar 5 segundos
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si el objeto se mediaPlayer existe se hará la operación
                if(mediaPlayer!=null){
                    int posicion = mediaPlayer.getCurrentPosition();
                    int posicionNueva=posicion+5000;
                    mediaPlayer.seekTo(posicionNueva);
                }
            }
        });

        //Switch para indicar si la reproducción funciona en bucle
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si el switch está marcado se reproducirá la canción en bucle, si no está marcado no
                if(aSwitch.isChecked()){
                    mediaPlayer.setLooping(true);
                }else{
                    mediaPlayer.setLooping(false);
                }
            }
        });

        //Botón para reproducir el vídeo guardado en raw
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoRaw();
            }
        });

        //Botón para reproducir el vídeo almacenado en un servidor web
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoOnline();
            }
        });

    }

    public void videoRaw(){
        //Enlazamos la vista con el recurso de vídeo
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));

        //Creamos el objeto que se ocupara de controlar la reproducción
        MediaController mediaController=new MediaController(this);
        //Lo asociamos al videoView
        videoView.setMediaController(mediaController);
        //Fijamos el controlar del vídeo a la vista del vídeo
        mediaController.setAnchorView(videoView);

        videoView.start();
    }

    public void videoOnline(){
        //Enlazamos la vista con el recurso de vídeo
        videoView.setVideoURI(Uri.parse("https://www.scratchya.com.ar/video1.mp4"));

        //Creamos el objeto que se ocupara de controlar la reproducción
        MediaController mediaController=new MediaController(this);
        //Lo asociamos al videoView
        videoView.setMediaController(mediaController);
        //Fijamos el controlar del vídeo a la vista del vídeo
        mediaController.setAnchorView(videoView);

        videoView.start();
    }
}