package com.example.lab3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class Fragment2 extends Fragment {


    private VideoView videoView;
    private TabLayout tabLayout;

    private String videoResourceName;

    public Fragment2(String nameVideo){
        videoResourceName = nameVideo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentmir2, container, false);
        videoView = view.findViewById(R.id.my_video);
        tabLayout = getActivity().findViewById(R.id.tabs);

        Button playButton = view.findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int  videoResourceID =  getResources().getIdentifier(videoResourceName, "raw", getContext().getPackageName());
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), videoResourceID);
                videoView.setMediaController(new MediaController(getContext()));
                videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + videoResourceID));

                videoView.requestFocus();
                videoView.start();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // videoView.stopPlayback();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;

    }




}

