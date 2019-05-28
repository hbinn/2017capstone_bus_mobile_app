package com.example.absin.firebasebus;

/**
 * Created by absin on 2019-05-28.
 */

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

/**
 * Created by absin on 2019-05-18.
 */



public class Alarm_stationActivity extends AppCompatActivity {

    final String TAG = "Station_After_Number";
    public String dataKey = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl;
    private String requestUrl2;

    ArrayList<station_after> list = null;
    ArrayList<station_tracker> tracker_list = null;

    station_tracker tracker = null; //ArrayList인 tracker_list에 값을 집어넣어줄 tracker 객체
    station_after bus = null;
    RecyclerView recyclerView;
    String routeId;

    ImageView refreshBtn;

    private Parcelable recyclerViewState;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_station);

        refreshBtn = (ImageView) findViewById(R.id.refresh_btn);
        Intent intent = getIntent();
        routeId = intent.getStringExtra("RouteId");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewSAN);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        MyAsyncTask3 myAsyncTask;
        myAsyncTask = new MyAsyncTask3();
        myAsyncTask.execute();

        //recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
                MyAsyncTask3 myAsyncTask2 = new MyAsyncTask3();
                myAsyncTask2.execute();
                Toast.makeText(getApplicationContext(), "새로고침 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*계획
    1. 먼저 버스위치 정보 조회를 한다 --> station_tracker class의 stationId 값이 채워짐
    2. 그 다음 버스노선죄회를 한다.--> station_after class의 stationId와 mobileNo, stationName의 값이 채워짐
    3. 값을 다 채워서 어답터를 실행하기 전에 1의 데이터 리스트와 2의 데이터리스트의 stationId를 비교한다.
    4. 1과 동일한 stationId를 가지고 있는 2의 station_after 객체의 tracker를 1로 만들어준다 (현재 버스가 그 정류장에 지나가고 있다는 소리
    5. 어답터 실행
        5-1. 어답터에서는 tracker의 값이 1인 station_after 객체만 tv_image를 visible하게 바꿔준다.
     */

    public class MyAsyncTask3 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            requestUrl2 = "http://openapi.gbis.go.kr/ws/rest/buslocationservice?serviceKey=" +dataKey+ "&routeId=" + routeId;
            try {
                boolean b_stationId = false;

                URL url = new URL(requestUrl2);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            tracker_list = new ArrayList<station_tracker>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("busLocationList") && tracker != null) {
                                tracker_list.add(tracker);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("busLocationList")){
                                tracker = new station_tracker();
                            }
                            if(parser.getName().equals("stationId")) b_stationId = true;

                            break;
                        case XmlPullParser.TEXT:
                            if(b_stationId){
                                tracker.setStationId(parser.getText());
                                b_stationId = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //첫번째 데이터를 버스 위치 정보 조회 api로 받은 다음에 두번째 정보를 받으러 감
            requestUrl = "http://openapi.gbis.go.kr/ws/rest/busrouteservice/station?serviceKey=" +dataKey+ "&routeId=" + routeId;
            try {
                boolean b_stationName = false;
                boolean b_mobileNo =false;
                boolean b_stationId = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));


                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<station_after>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("busRouteStationList") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("busRouteStationList")){
                                bus = new station_after();
                            }
                            if (parser.getName().equals("mobileNo")) b_mobileNo = true;
                            if(parser.getName().equals("stationId")) b_stationId = true;
                            if(parser.getName().equals("stationName")) b_stationName = true;

                            break;
                        case XmlPullParser.TEXT:
                            if(b_mobileNo){
                                bus.setMobileNo(parser.getText());
                                b_mobileNo = false;
                            } else if(b_stationId) {
                                bus.setStationId(parser.getText());
                                b_stationId = false;
                            } else if(b_stationName) {
                                bus.setStationName(parser.getText());
                                b_stationName = false;
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

        //   @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            for(int i=0; i< tracker_list.size(); i++){
                station_tracker sub = tracker_list.get(i);
                String sub_string = sub.stationId;

                for(int j=0; j<list.size(); j++){
                    station_after sub2 = list.get(j);
                    String sub_string2 = sub2.stationId;

                    if(sub_string.equals(sub_string2)){
                        sub2.setTracker(1);
                        list.set(j, sub2);
                    }
                }
            }


            //어답터 연결
            Alarm_station_Adapter adapter = new Alarm_station_Adapter(Alarm_stationActivity.this, getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }
    }

}
