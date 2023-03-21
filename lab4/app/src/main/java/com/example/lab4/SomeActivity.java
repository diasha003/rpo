package com.example.lab4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class SomeActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;
    private TextView textView;
    private ImageView imageView;

    private Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some);
        mDetector=new GestureDetectorCompat(this, new MyGestListener());

        textView = findViewById(R.id.some);
        back = findViewById(R.id.back);

        imageView = findViewById(R.id.image_some);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            textView.setText("onFling: " + event1.toString()+event2.toString());
            int resourceId = getResources().getIdentifier("on_scroll", "drawable", getPackageName());
            imageView.setImageResource(resourceId);
            //Toast.makeText (getApplicationContext(), "onFling", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent event) {
            textView.setText("onLongPress: " + event.toString());
            int resourceId = getResources().getIdentifier("on_long_press", "drawable", getPackageName());
            imageView.setImageResource(resourceId);
            //Toast.makeText (getApplicationContext(), "onLongPress", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onShowPress(MotionEvent event) {
            textView .setText("onShowPress: " + event.toString());
            int resourceId = getResources().getIdentifier("on_single_tap_confirmed", "drawable", getPackageName());
            imageView.setImageResource(resourceId);
            //Toast.makeText (getApplicationContext(), "onShowPress", Toast.LENGTH_SHORT).show();
        }
    }
}
