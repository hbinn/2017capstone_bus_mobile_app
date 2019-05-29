package com.example.absin.firebasebus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    protected ArrayList<AlarmInfo> alarmList;
    protected ListView mLIstView;
    protected alarm_MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_main);

        Button btnPlus = (Button) findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetAlarmInfo.class);
                startActivityForResult(intent, 0);
            }
        });

        alarmList = new ArrayList<AlarmInfo>();
        mLIstView = (ListView) findViewById(R.id.listView);
        myAdapter = new alarm_MyAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            AlarmInfo ai = (AlarmInfo) data.getParcelableExtra("alarmInfo");
            alarmList.add(ai);

            myAdapter.addItem(ai.getDays(), ai.getStrStartTime(), ai.getStrEndTime(), ai.getGapTime(),
                    ai.getBus_number(), ai.getBus_station());
            mLIstView.setAdapter(myAdapter);
        }
    }
}
