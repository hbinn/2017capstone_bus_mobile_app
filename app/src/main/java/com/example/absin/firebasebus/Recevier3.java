package com.example.absin.firebasebus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by absin on 2019-05-26.
 */

public class Recevier3 extends BroadcastReceiver  {

    //AlarmManager am;
    //String endTime;
    int gapTime;
    String keyword1;
    String keyword2;
    String bus_number;
    int REQCODE2;
    String result = null;
    String endTime2;
    Context context;
    //keyword1이 정류장이고 keyword2가 버스 아이디

    @Override
    public void onReceive(Context context, Intent intent) {
        context = context;
        //endTime = intent.getStringExtra("endTime");
        gapTime = intent.getIntExtra("gapTime", 0);
        keyword1 =  intent.getStringExtra("StationId");
        keyword2 = intent.getStringExtra("RouteId");
        bus_number = intent.getStringExtra("BusNumber");
        REQCODE2 = intent.getIntExtra("REQCODE2", -1);
        endTime2 = intent.getStringExtra("endTime2");


        Intent intent2 = new Intent(context, Recevier2.class);
        int REQCODE2 = intent.getIntExtra("REQCODE2", -1);
        //final PendingIntent sender = PendingIntent.getBroadcast(this, 100, intent2, 0);
        final PendingIntent sender = PendingIntent.getBroadcast(context, REQCODE2, intent2, 0);
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        String sub_endTime[] = endTime2.split(" ");
        int endtime_hour = Integer.parseInt(sub_endTime[0]);
        int endtime_minute = Integer.parseInt(sub_endTime[1]);

        Calendar end = Calendar.getInstance();
        int real_hour = end.get(Calendar.HOUR_OF_DAY);
        int real_minute= end.get(Calendar.MINUTE);

        System.out.println("리시버 3에서 일어나는 일");
        System.out.println(endtime_hour+ " " + endtime_minute);
        System.out.println(real_hour+ " " +real_minute);
        if(endtime_hour<=real_hour && endtime_minute<=real_minute) {
            System.out.println("실행확인");
            alarmManager.cancel(sender);
        }else {
            MyAsyncTask_alarm myAsyncTask = new MyAsyncTask_alarm(context, keyword1, keyword2);
            try {
                result = myAsyncTask.execute().get();
            } catch (Exception e) {
                e.printStackTrace();
            }


            String[] arrResult = result.split(",");
            int pTime1 = Integer.parseInt(arrResult[0]);
            int pTime2 = Integer.parseInt(arrResult[1]);

            if ((pTime1 > gapTime - 4 && pTime1 < gapTime + 4)
                    || (pTime2 > gapTime - 4 && pTime2 < gapTime + 4)) {

                Intent intent_dialog = new Intent(context.getApplicationContext(), DialogActivity.class);
                //intent_dialog.putExtra("endTime", endTime);
                intent_dialog.putExtra("predict", result);
                intent_dialog.putExtra("BusNumber", bus_number);
                intent_dialog.putExtra("REQCODE2", REQCODE2);
                intent_dialog.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //이거때문에 뒤로가기 하면 아무것도 안되는 것 같음. 원래 설정화면에서 알람이 발생했을때 뒤로 가면 그전의 화면이었던 설정화면이 나왔었다.
                context.startActivity(intent_dialog);
            }
        }
    }
}
