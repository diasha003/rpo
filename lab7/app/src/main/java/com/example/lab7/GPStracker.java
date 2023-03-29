package com.example.lab7;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
    Context context;
    TextView tvOut;
    public GPStracker(Context c, TextView text){
        context = c;
        tvOut = text;
    }

    public Location getLocation(){

        // проверяем что разрешение получено
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context,"Разрешение не предоставлено",Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); //подключаем менеджер локаций
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if(isGPSEnabled){ // проверяем что GPS включен
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            tvOut.setText("Enabled: true");
            return l;
        }else {
            tvOut.setText("Enabled: false");
            Toast.makeText(context,"Пожалуйста, включите GPS! =)", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
