package com.example.memory;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MemoriaStart extends AppCompatActivity {

    private Button mStart;
    private Button mExit;
    private Button mInfo;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        mStart = (Button) findViewById(R.id.Start);
        mExit = (Button) findViewById(R.id.Exit);
        mInfo = (Button) findViewById(R.id.Info);



        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertbox = new AlertDialog.Builder(getApplicationContext());
                alertbox.setTitle("Поздравляем!");
                //alertbox.setCancelable(true);

                String TextToast = "Игра закончена \nХодов: ";
                alertbox.setMessage(TextToast);


                AlertDialog alertbox2 = alertbox.create();
                alertbox2.show();


                //String TextToast = "Отлично!";
               //Toast.makeText (getApplicationContext(), TextToast, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
