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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class AllActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener
{

    private TextView textView;
    private GestureDetectorCompat mDetector;
    private ImageView imageView;

    private Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
        textView = findViewById(R.id.all);
        back = findViewById(R.id.back);
        imageView = findViewById(R.id.image_all);


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


    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        textView.setText("onSingleTapConfirmed: " + motionEvent.toString());
        int resourceId = getResources().getIdentifier("on_single_tap_confirmed", "drawable", getPackageName());
        imageView.setImageResource(resourceId);
        //Toast.makeText (getApplicationContext(), "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        textView.setText("onDoubleTap: " + motionEvent.toString());
        int resourceId = getResources().getIdentifier("on_double_tap", "drawable", getPackageName());
        imageView.setImageResource(resourceId);
       //Toast.makeText (getApplicationContext(), "onDoubleTap", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        textView.setText("onDoubleTapEvent: " + motionEvent.toString());
        int resourceId = getResources().getIdentifier("on_double_tap", "drawable", getPackageName());
        imageView.setImageResource(resourceId);

        //Toast.makeText (getApplicationContext(), "onDoubleTapEvent", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        textView.setText("onDown: " + motionEvent.toString());
        int resourceId = getResources().getIdentifier("on_double_tap", "drawable", getPackageName());
        imageView.setImageResource(resourceId);
        //Toast.makeText (getApplicationContext(), "onDown", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {
        textView.setText("onShowPress: " + motionEvent.toString());
        imageView.setImageResource(0);
        //Toast.makeText (getApplicationContext(), "onShowPress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        textView.setText("onSingleTapUp: " + motionEvent.toString());
        imageView.setImageResource(0);
        //Toast.makeText (getApplicationContext(), "onSingleTapUp", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        textView.setText("onScroll: " + motionEvent.toString()+motionEvent1.toString());
        int resourceId = getResources().getIdentifier("on_scroll", "drawable", getPackageName());
        imageView.setImageResource(resourceId);
        //Toast.makeText (getApplicationContext(), "onScroll", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {
        textView.setText("onLongPress: " + motionEvent.toString());
        int resourceId = getResources().getIdentifier("on_long_press", "drawable", getPackageName());
        imageView.setImageResource(resourceId);
        //Toast.makeText (getApplicationContext(), "onLongPress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        textView.setText("onFling: " + motionEvent.toString()+motionEvent1.toString());
        int resourceId = getResources().getIdentifier("on_scroll", "drawable", getPackageName());
        imageView.setImageResource(resourceId);
        //Toast.makeText (getApplicationContext(), "onFling", Toast.LENGTH_SHORT).show();
        return true;
    }
}
