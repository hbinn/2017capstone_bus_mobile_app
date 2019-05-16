package com.example.absin.firebasebus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;


/**
 * Created by absin on 2019-05-16.
 */public class search_barJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bar);




        TabHost tabHost = (TabHost)findViewById(R.id.th);
        tabHost.setup();

        TabHost.TabSpec tabSpecBus = tabHost.newTabSpec("BUS").setIndicator("버스");
        tabSpecBus.setContent(R.id.bus);
        tabHost.addTab(tabSpecBus);

        TabHost.TabSpec tabSpecBusStop = tabHost.newTabSpec("BUSSTOP").setIndicator("정류장");
        tabSpecBusStop.setContent(R.id.busstop);
        tabHost.addTab(tabSpecBusStop);

        tabHost.setCurrentTab(0);
    }
}


