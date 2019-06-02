package com.example.absin.firebasebus;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class setting  extends AppCompatActivity {

    LinearLayout notification;
    LinearLayout version;
    LinearLayout developer;
    LinearLayout api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.setting) ;

        notification = (LinearLayout) findViewById(R.id.setting_notification);
        version = (LinearLayout) findViewById(R.id.setting_version);
        developer = (LinearLayout) findViewById(R.id.setting_developer);
        api = (LinearLayout) findViewById(R.id.setting_opensource);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), setting_notificationActivity.class);
                startActivity(intent);
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = (cal.get(Calendar.MONTH)) +1;
                int date = cal.get(Calendar.DAY_OF_MONTH);

                String info1 = Integer.toString(year)+"년 " + Integer.toString(month)+"월" + Integer.toString(date)+"일";

                AlertDialog.Builder dlg = new AlertDialog.Builder(setting.this);
                dlg.setTitle("   버전정보");
                dlg.setMessage("\n\t"+ info1 + "\n\t가장 최신의 릴리즈 버전입니다.");
                dlg.setIcon(R.drawable.ic_candidate4);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), setting_developerActivity.class);
                startActivity(intent);
            }
        });

        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), setting_apiActivity.class);
                startActivity(intent);
            }
        });


    }


}
