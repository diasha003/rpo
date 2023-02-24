package com.example.memory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private GridView mGrid;
    private GridAdapter mAdapter;

    private TextView mStepScreen;
    private  Chronometer mTimeScreen;

    private Integer StepCount;

    private Button mBack;

    private Button mRestart;

    /** Called when the activity is first created. */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrid = (GridView)findViewById(R.id.field);
        mGrid.setNumColumns(4);
        mGrid.setEnabled(true);

        mBack = (Button) findViewById(R.id.Back);
        mRestart = (Button) findViewById(R.id.Restart);

        mAdapter = new GridAdapter(this, 4, 4);
        mGrid.setAdapter(mAdapter);


        mTimeScreen = (Chronometer)findViewById(R.id.timeview);
        mStepScreen = (TextView) findViewById(R.id.stepview);


       StepCount = 0;
       mStepScreen.setText(StepCount.toString());
       mTimeScreen.start();


       mBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

       mRestart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = getIntent();
               finish();
               startActivity(intent);
           }
       });

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //i - position, l-id
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                mAdapter.checkOpenCells();
                if (mAdapter.openCell(i)){
                    StepCount++;
                    mStepScreen.setText(StepCount.toString());
                }


                if(mAdapter.checkGameOver()){

                    mTimeScreen.stop();
                   /* String time = mTimeScreen.getText().toString();
                    String TextToast = "Игра закончена nХодов: " + StepCount.toString() + "nВремя: " + time;
                    Toast.makeText (getApplicationContext(), TextToast, Toast.LENGTH_SHORT).show();*/
                    ShowGameOver();
                }

            }

            public void ShowGameOver(){


                AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this); //диологовое окно
                alertbox.setTitle("Поздравляем!");
                alertbox.setCancelable(true);

                String time = mTimeScreen.getText().toString();
                String TextToast = "Игра закончена \nХодов: " + StepCount.toString() + "\nВремя: " + time;
                alertbox.setMessage(TextToast);

                alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                AlertDialog alertbox2 = alertbox.create();
                alertbox2.show();
               // alertbox.show(); //показываем окно
            }

        });

    }

}