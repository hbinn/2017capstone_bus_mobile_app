package com.example.absin.firebasebus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by absin on 2019-05-18.
 */

public class Station_After_Number extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_after_number);

        Intent intent = getIntent();
        String routeId = intent.getStringExtra("RouteId");

        ListView List = (ListView) findViewById(R.id.statin_After_number);
        String array_ex[] = {"정류장이름","정류장이름","정류장이름","정류장이름","정류장이름","정류장이름","정류장이름" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, array_ex);
        List.setAdapter(adapter);

    }
}
