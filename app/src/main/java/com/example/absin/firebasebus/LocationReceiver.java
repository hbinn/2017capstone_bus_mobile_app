package com.example.absin.firebasebus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Created by Junny_PC on 2019-06-11.
 */
// ㅇㅇ

public class LocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isEntering = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        if (isEntering)
            Toast.makeText(context, "목표 지점에 접근중..", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "목표 지점에서 벗어납니다.", Toast.LENGTH_LONG).show();
    }
}
