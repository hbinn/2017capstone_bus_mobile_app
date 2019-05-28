package com.example.absin.firebasebus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Junny_PC on 2019-05-25.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private AlarmManager am2;
    String bus_routeId;
    String bus_number;
    String bus_stationId;

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean[] week = intent.getBooleanArrayExtra("weekday");
        bus_routeId = intent.getStringExtra("RouteId");
        bus_number = intent.getStringExtra("BusNumber");
        bus_stationId = intent.getStringExtra("StationId");


        am2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent2 = new Intent(context, Recevier2.class);
        intent2.putExtra("RouteId", bus_routeId);
        intent2.putExtra("BusNumber", bus_number);
        intent2.putExtra("StationId", bus_stationId);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 100, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        if (!week[cal.get(Calendar.DAY_OF_WEEK)]) {
            return;
        }

        long interval = 1000 * 60; //1분마다 실행이된다. 2분은 뒤에 *2하면 됨
        am2.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, pIntent);




    }
}