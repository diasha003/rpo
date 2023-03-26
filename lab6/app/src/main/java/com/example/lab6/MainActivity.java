package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View.OnClickListener btnClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click(v.getId());
            }
        };
        ((Button)findViewById(R.id.button1)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.button2)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.button3)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.button4)).setOnClickListener(btnClick);

    }

    protected void Click(int view){
        Intent intent=null;
        switch (view){
            case R.id.button1: intent=new Intent(this,AudioActivity.class);   break;
            case R.id.button2: intent=new Intent(this,GalleryActivity.class); break;
            case R.id.button3: intent=new Intent(this,CameraActivity.class); break;
            case R.id.button4: intent=new Intent(this,VideoActivity.class); break;
            default: break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}