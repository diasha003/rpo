package com.example.lab6;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private Button b_Start, b_Pause, b_Resume, b_Stop;


    private VideoView videoPlayer;
    private int stopPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        b_Start = findViewById(R.id.b_Start);
        b_Pause = findViewById(R.id.b_Pause);
        b_Resume = findViewById(R.id.b_Resume);;
        b_Stop = findViewById(R.id.b_Stop);

        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);

        b_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String videoPath = ((EditText) findViewById(R.id.et_MediaPath)).getText().toString();
                    videoPlayer.setVideoPath(videoPath);

                    videoPlayer.start();
                } catch (Exception e) {
                    showMessage("Ошибка воспроизведения");
                }
            }
        });

        b_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoPlayer.isPlaying()) {
                    videoPlayer.pause();
                    stopPosition = videoPlayer.getCurrentPosition();
                }
            }
        });

        b_Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!videoPlayer.isPlaying()) {
                    videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            videoPlayer.seekTo(stopPosition); // перемотка на 5 секунд
                        }
                    });

                    videoPlayer.start();
                }
            }
        });

        b_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlayer.stopPlayback();
            }
        });
    }

    private void showMessage(String text){
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
