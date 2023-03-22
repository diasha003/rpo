package com.example.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;

import android.gesture.Prediction;
import android.widget.Toast;
import android.gesture.Gesture;
import java.util.ArrayList;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements  OnGesturePerformedListener{

    private GestureLibrary objGestureLib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objGestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if(!objGestureLib.load()){
            finish();
        }
        GestureOverlayView objGestureOverlay = (GestureOverlayView)findViewById(R.id.WidgetGesture);
        objGestureOverlay.addOnGesturePerformedListener(this);

    }

    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
        ArrayList<Prediction> objPrediction = objGestureLib.recognize(gesture);
        if (objPrediction.size() >0){
            if (objPrediction.get(0).score > 1) {
                String gestureName = objPrediction.get(0).name;
                Toast.makeText(this, gestureName, Toast.LENGTH_SHORT).show();
                if (gestureName.equals("Stop")) {this.finish();}
                if (gestureName.equals("About")){
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

            }
            else {
                Toast.makeText(getApplicationContext(), "Жест неизвестен", Toast.LENGTH_SHORT).show();
            }

        }

    }

}