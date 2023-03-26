package com.example.lab6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AudioActivity extends AppCompatActivity {
        private MediaPlayer mediaPlayer;
        private SeekBar seekBar;
        private Handler handler;

        private Button b_Start, b_Pause, b_Stop, b_Resume;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_audio);

            b_Start = findViewById(R.id.playButton);
            b_Resume = findViewById(R.id.resumeButton);
            b_Pause = findViewById(R.id.pauseButton);
            b_Stop = findViewById(R.id.stopButton);


            String audioPath = ((EditText) findViewById(R.id.et_MediaPath)).getText().toString();
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(audioPath);
                mediaPlayer.prepare();
            } catch (IOException e) {
               e.printStackTrace();
            }
            //mediaPlayer = MediaPlayer.create(this, R.raw.music);

            seekBar = findViewById(R.id.volumeControl);
            handler = new Handler();

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser) {
                        mediaPlayer.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    seekBar.setProgress(0);
                }
            });

            b_Start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        String audio = ((EditText) findViewById(R.id.et_MediaPath)).getText().toString();
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(audio);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            updateSeekBar();
                   } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Ошибка воспроизведения", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }


                }
            });

            b_Resume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.start();
                    updateSeekBar();
                }
            });

            b_Pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.pause();
                    handler.removeCallbacksAndMessages(null);
                }
            });

            b_Stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.pause();
                    seekBar.setProgress(0);
                    handler.removeCallbacksAndMessages(null);
                }
            });
        }

        private void updateSeekBar() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                }
            }, 1000);
        }


        @Override
        protected void onDestroy() {
            super.onDestroy();
            if(mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            handler.removeCallbacksAndMessages(null);
        }
    }

