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


public class bus_list extends AppCompatActivity {
    final String TAG = "bus_list";

    public String dataKey = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl;

    public String dataKey2 = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl2;

    ArrayList<bus_list_item> list = null;
    bus_list_item list_item = null;


    ArrayList<bus_time> list2 = null;
    bus_time list2_item = null;

    RecyclerView recyclerView;
   // RecyclerView recyclerView2;
    String stationId;

    String g_routeId = null;
    String g_staOrder = null;

    private Parcelable recyclerViewState;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_list);

        Intent intent = getIntent();
        stationId = intent.getStringExtra("stationId");

        recyclerView = (RecyclerView) findViewById(R. id. recycler_bus_list);
        recyclerView.setHasFixedSize(true);

//        recyclerView2 = (RecyclerView) findViewById(R. id. recycler_bus_list);
//        recyclerView2.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
       // recyclerView2.setLayoutManager(layoutManager);

        MyAsyncTask4 myAsyncTask;
        myAsyncTask = new MyAsyncTask4();
        myAsyncTask.execute();
    }

    public class MyAsyncTask4 extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapi.gbis.go.kr/ws/rest/busstationservice/route?serviceKey="+dataKey+"&stationId="+stationId;
            try {
                boolean b_routeName = false;
                boolean b_routeId = false;
               // boolean b_staOrder = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<bus_list_item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("busRouteList") && list_item != null) {
                                list.add(list_item);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("busRouteList")) {
                                list_item = new bus_list_item();
                            }
                            if (parser.getName().equals("routeName")) b_routeName = true;
                            if (parser.getName().equals("routeId")) b_routeId = true;
                         //   if (parser.getName().equals("staOrder")) b_staOrder = true;

                            break;
                        case XmlPullParser.TEXT:
                            if (b_routeName) {
                                list_item.setrouteName(parser.getText());
                                b_routeName = false;
                            }
                            if (b_routeId) {
                                list_item.setrouteId(parser.getText());
                                b_routeId = false;
                            }
//                            else if (b_staOrder) {
//                                list_item.setstaOrder(parser.getText());
//                                b_staOrder = false;
//                            }

                            break;
                    }

                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }




           // http://openapi.gbis.go.kr/ws/rest/busarrivalservice/station?serviceKey=vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D&stationId=200000078
            requestUrl2 = "http://openapi.gbis.go.kr/ws/rest/busarrivalservice/station?serviceKey="+dataKey2+"&stationId="+stationId;
         //   requestUrl2 = "http://openapi.gbis.go.kr/ws/rest/busarrivalservice?serviceKey=vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D&stationId=203000035&routeId=234000013&staOrder=83";
            try {
                boolean b_predictTime1 = false;
                boolean b_predictTime2 = false;
                boolean b_routeId = false;

                URL url = new URL(requestUrl2);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list2 = new ArrayList<bus_time>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("busArrivalList") && list2_item != null) {
                                list2.add(list2_item);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("busArrivalList")) {
                                list2_item = new bus_time();
                            }
                            if (parser.getName().equals("predictTime1")) b_predictTime1 = true;
                            if (parser.getName().equals("predictTime2")) b_predictTime2 = true;
                            if (parser.getName().equals("routeId")) b_routeId = true;

                            break;
                        case XmlPullParser.TEXT:
                            if (b_predictTime1) {
                                list2_item.setPredictTime1(parser.getText());
                                b_predictTime1 = false;
                            }
                             if (b_predictTime2) {
                                list2_item.setPredictTime2(parser.getText());
                                b_predictTime2 = false;
                            }

                            if (b_routeId) {
                                list2_item.setRouteId(parser.getText());
                                b_routeId = false;
                            }

                            break;
                    }

                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("파싱다됨");


            //}

            return null;
        }
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            System.out.println("밖");

            for(int i=0; i<list.size(); i++){
                bus_list_item sub1 = list.get(i);
                for(int j=0; j<list2.size(); j++) {
                    bus_time sub2 = list2.get(j);
                    if(sub1.routeId.equals(sub2.routeId)){
                        if(sub2.predictTime1.equals("-1")){
                            sub2.predictTime1 = "도착정보없음";
                        }
                        if(sub2.predictTime2.equals("-1")){
                            sub2.predictTime2="도착정보없음";
                        }
                        sub1.setPredictTime1(sub2.predictTime1);
                        sub1.setPredictTime2(sub2.predictTime2);
                        System.out.println(sub2.predictTime1+" "+sub2.getPredictTime2());
                        list.set(i, sub1);
                        System.out.println("실행이됨");
                    }

                }
            }

            //어답터 연결
            bus_list_adapter adapter = new bus_list_adapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);

        }
    }
}