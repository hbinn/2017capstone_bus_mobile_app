package com.example.absin.firebasebus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;


/**
 * Created by absin on 2019-05-16.
 */public class search_barJ extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bar);


        Button btn1 = (Button) findViewById(R.id.btn);
        Button btn2 = (Button) findViewById(R.id.btn2);

        TabHost tabHost = (TabHost)findViewById(R.id.th);
        tabHost.setup();


        TabHost.TabSpec tabSpecBus = tabHost.newTabSpec("BUS").setIndicator("버스");
        tabSpecBus.setContent(R.id.bus);
        tabHost.addTab(tabSpecBus);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NumberSearch.class);
                startActivity(intent);
            }
        });


        TabHost.TabSpec tabSpecBusStop = tabHost.newTabSpec("BUSSTOP").setIndicator("정류장");
        tabSpecBusStop.setContent(R.id.busstop);
        tabHost.addTab(tabSpecBusStop);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BusStop_activity.class);
                startActivity(intent);
            }
        });


      //  tabHost.setCurrentTab(0);



    }
}


