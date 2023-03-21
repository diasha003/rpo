package com.example.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button all;
    private Button some;
    private Button about;

    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        all = findViewById(R.id.button1);
        some = findViewById(R.id.button2);
        about = findViewById(R.id.button3);
        exit = findViewById(R.id.button4);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), AllActivity.class);
                startActivity(intent1);
            }
        });

        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(view.getContext(), SomeActivity.class);
                startActivity(intent2);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this); //диологовое окно
                alertbox.setTitle("About");
                alertbox.setCancelable(true);


                String TextToast = "That application is developed by Fursevich Diana, a student of the PO-7 group. It allows you to use all standard gesture nor only some of them.";
                alertbox.setMessage(TextToast);

                alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertbox2 = alertbox.create();
                alertbox2.show();
        }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}