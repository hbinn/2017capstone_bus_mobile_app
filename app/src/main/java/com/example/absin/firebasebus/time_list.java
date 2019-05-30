package com.example.absin.firebasebus;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class time_list extends AppCompatActivity {
    final String TAG = "time_list";
    public String dataKey = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl;

    ArrayList<bus_time> list = null;
    bus_time list_item = null;

    RecyclerView recyclerView;
    String stationId;
    String routeId;
    String staOrder;

    private Parcelable recyclerViewState;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_list);

        Intent intent = getIntent();
        stationId = intent.getStringExtra("stationId");

        recyclerView = (RecyclerView) findViewById(R. id. recycler_bus_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        MyAsyncTask5 myAsyncTask;
        myAsyncTask = new MyAsyncTask5();
        myAsyncTask.execute();
    }
    public class MyAsyncTask5 extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapi.gbis.go.kr/ws/rest/busarrivalservice?serviceKey="+dataKey+"&stationId="+stationId+"&routeId="+routeId+"&staOrder="+staOrder;
            try {
                boolean b_predict1 = false;
                boolean b_predict2 = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<bus_time>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("busArrivalList") && list_item != null) {
                                list.add(list_item);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("busArrivalList")) {
                                list_item = new bus_time();
                            }
                            if (parser.getName().equals("predictTime1")) b_predict1 = true;
                            if (parser.getName().equals("predictTime2")) b_predict2 = true;

                            break;
                        case XmlPullParser.TEXT:
                            if (b_predict1) {
                                list_item.setPredictTime1(parser.getText());
                                b_predict1 = false;
                            }else if(b_predict2){
                                list_item.setPredictTime2(parser.getText());
                                b_predict2 = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //어댑터 연결2

            time_adapter adapter = new time_adapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);

        }
    }
}
