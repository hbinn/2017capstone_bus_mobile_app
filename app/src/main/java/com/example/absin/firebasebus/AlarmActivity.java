package com.example.absin.firebasebus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class AlarmActivity extends AppCompatActivity {
    //AlarmManager am;
    protected ArrayList<AlarmInfo> alarmList;
    protected ListView mLIstView;
    protected alarm_MyAdapter myAdapter;
    private final String fileName = "alarm.list";
    LinearLayout imgDefault;

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

        TextView txtEdit = (TextView) findViewById(R.id.txtEdit);
        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAdapter.isEditClicked())
                    myAdapter.editClick(false);
                else
                    myAdapter.editClick(true);
            }
        });

        //am = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmList = new ArrayList<AlarmInfo>();
        mLIstView = (ListView) findViewById(R.id.listView);
        myAdapter = new alarm_MyAdapter(this, AlarmActivity.this);

        loadFile();
        setLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            AlarmInfo ai = (AlarmInfo) data.getParcelableExtra("alarmInfo");
            alarmList.add(ai);

            myAdapter.addItem(""+ ai.getRequestCode1(), ""+ai.getRequestCode2(),
                    ai.getDays(), ai.getStrStartTime(), ai.getStrEndTime(), "" + ai.getGapTime(),
                    ai.getBus_number(), ai.getBus_station());
            mLIstView.setAdapter(myAdapter);

            myAdapter.editClick(false);

            setLayout();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        putFile();
    }

    public AlertDialog.Builder showDial() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("선택한 알람을 삭제하시겠습니까?");

        return builder;
    }

//    public void cancelAlarm(int reqCode1, int reqCode2) {
//        am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        Intent intent1 = new Intent(this, AlarmReceiver.class);
//        Intent intent2 = new Intent(this, Recevier2.class);
//        PendingIntent sender1 = PendingIntent.getBroadcast(this, reqCode1, intent1, 0);
//        PendingIntent sender2 = PendingIntent.getBroadcast(this, reqCode2, intent2, 0);
//        am.cancel(sender2);
//        am.cancel(sender1);
//    }

    private void putFile() {
        File file = new File(getFilesDir(), fileName);
        FileWriter fw = null;
        BufferedWriter bufwr = null;

        try {
            fw = new FileWriter(file);
            bufwr = new BufferedWriter(fw);

            for (int i=0; i<myAdapter.getCount(); i++) {
                bufwr.write(myAdapter.getItem(i).toString());
                bufwr.newLine();
            }

            bufwr.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (bufwr != null) {
                bufwr.close();
            }

            if (fw != null) {
                fw.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFile() {
        File file = new File(getFilesDir(), fileName);
        FileReader fr = null;
        BufferedReader bufrd = null;
        String str;

        if (file.exists()) {
            try {
                fr = new FileReader(file);
                bufrd = new BufferedReader(fr);

                while ((str = bufrd.readLine()) != null) {
                    myAdapter.addItem(str);
                }

                bufrd.close();
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mLIstView.setAdapter(myAdapter);
    }

    public void setLayout() {
        imgDefault = (LinearLayout) findViewById(R.id.imgDefault);
        if (myAdapter.getCount() > 0) {
            imgDefault.setVisibility(View.INVISIBLE);
            mLIstView.setVisibility(View.VISIBLE);
        }
        else {
            imgDefault.setVisibility(View.VISIBLE);
            mLIstView.setVisibility(View.INVISIBLE);
        }
    }

}
