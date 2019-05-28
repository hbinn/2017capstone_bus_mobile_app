package com.example.absin.firebasebus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by absin on 2019-05-26.
 */

public class Recevier2 extends BroadcastReceiver  {

    String keyword1;
    String keyword2;
    String bus_number;
    String result = null;
    //keyword1이 정류장이고 keyword2가 버스 아이디

    @Override
    public void onReceive(Context context, Intent intent) {


        keyword1 =  intent.getStringExtra("StationId");
        keyword2 = intent.getStringExtra("RouteId");
        bus_number = intent.getStringExtra("BusNumber");

        MyAsyncTask_alarm myAsyncTask = new MyAsyncTask_alarm(context, keyword1, keyword2);
        try {
            result = myAsyncTask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(result.equals("null,null")){
            Toast.makeText(context.getApplicationContext(), "현재 운행중인 버스 정보 없음", Toast.LENGTH_SHORT).show();
        }else if(result.contains(",null")) {
            Toast.makeText(context.getApplicationContext(), "현재 운행중인 버스 정보 없음", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent_dialog = new Intent(context.getApplicationContext(), DialogActivity.class);
            intent_dialog.putExtra("predict", result);
            intent_dialog.putExtra("BusNumber", bus_number);
            intent_dialog.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //이거때문에 뒤로가기 하면 아무것도 안되는 것 같음. 원래 설정화면에서 알람이 발생했을때 뒤로 가면 그전의 화면이었던 설정화면이 나왔었다.
            context.startActivity(intent_dialog);
        }



    }




}
