package com.example.absin.firebasebus;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Junny_PC on 2019-06-10.
 */

public class GetOffService extends Service {
    LocationManager locationManager;
    LocationListener locationListener;
    LocationReceiver locationReceiver;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        locationReceiver = new LocationReceiver();
        IntentFilter filter = new IntentFilter("com.androidhuman.example.Location");
        registerReceiver(locationReceiver, filter);

        Intent intent = new Intent("com.androidhuman.example.Location");
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        locationManager.addProximityAlert(37,-122,100f,-1, proximityIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(locationReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
