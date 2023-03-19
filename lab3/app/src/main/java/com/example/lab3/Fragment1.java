package com.example.lab3;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {

    private Button backButton;
    private TextView textViewDescription;
    private TextView textViewTitle;
    private ImageView imageView;

    private String  decriptionFr;
    private String pathFr;

    private String titleFr;


    public Fragment1(String path , String decription, String title){
        pathFr = path;
        decriptionFr = decription;
        titleFr = title;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentmir1, container, false);
        backButton = view.findViewById(R.id.back);
        textViewDescription = view.findViewById(R.id.my_text2);
        textViewTitle = view.findViewById(R.id.my_text);
        imageView = view.findViewById(R.id.my_image);

        textViewTitle.setText(titleFr);
        textViewDescription.setText(decriptionFr);

        int resourceId = getResources().getIdentifier(pathFr, "drawable", getContext().getPackageName());
        imageView.setImageResource(resourceId);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to return to the main page?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }
}

