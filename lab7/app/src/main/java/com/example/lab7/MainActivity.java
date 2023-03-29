package com.example.lab7;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private Button btnGetLoc, btnSaveDB, btnAbout;
    private TextView tvOut, tvLat;

    private SQLiteDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetLoc = (Button) findViewById(R.id.btnLocationSettings);
        btnSaveDB = (Button) findViewById(R.id.btnSaveDB);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        db = getBaseContext().openOrCreateDatabase("location.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS location (_id INTEGER PRIMARY KEY AUTOINCREMENT, lon TEXT, lat TEXT)");


        tvOut = findViewById(R.id.tvEnabledGPS);
        tvLat = findViewById(R.id.tvLatitudeGPS);


        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION }, 123); // запрос разрешение на использовние геопозиции
        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPStracker g = new GPStracker(getApplicationContext(), tvOut);
                Location l = g.getLocation(); // получаем координаты
                if(l != null){
                    double lat = l.getLatitude();  // широта
                    double lon = l.getLongitude(); // долгота
                    db.execSQL("INSERT INTO location(lon, lat)  VALUES ('" + lon + "', '" + lat + "');");
                    Toast.makeText(getApplicationContext(), "Широта: "+lat+"\nДолгота: "+lon, Toast.LENGTH_LONG).show();
                }
                btnSaveDB.setActivated(true);
                tvLat.setText("");
            }
        });

        btnSaveDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor query = db.rawQuery("SELECT * FROM location;", null);
                while(query.moveToNext()){
                    int id = query.getInt(0);
                    System.out.println(id);
                    String lon1 = query.getString(1);
                    String lat2 = query.getString(2);
                    tvLat.append(id + "\n" + "Latitude: " + lat2 + "\n" + "Longitude: " + lon1+"\n\n");
                }
                query.close();
                btnSaveDB.setActivated(false);
            }
        });

       btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this); //диологовое окно
                alertbox.setTitle("About");
                alertbox.setCancelable(true);


                String TextToast = "That application is developed by Fursevich Diana, a student of the PO-7 group. It allows you to get the coordinates of the device and track their changes. If you want to see save your saved geolocation data, click db.";
                alertbox.setMessage(TextToast);

                alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertbox2 = alertbox.create();
                alertbox2.show();
            }
        });

    }

}