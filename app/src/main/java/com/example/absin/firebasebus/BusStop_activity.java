package com.example.absin.firebasebus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class BusStop_activity extends AppCompatActivity {

    final String TAG = "BusStop_activity";
    public String dataKey = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl;
    ArrayList<BusStop_item> list = null;
    BusStop_item busStop = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busstop);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        AsyncTask
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }


    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapi.gbis.go.kr/ws/rest/busrouteservice/station?serviceKey=" + dataKey + "&routeId=200000085";
            try {

                boolean b_stationId =false;
                boolean b_stationName = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8")); //input stream으로부터 데이터 입력받기

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT: //파싱 시작 단계
                            list = new ArrayList<BusStop_item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("busRouteStationList") && busStop != null) {
                                list.add(busStop);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("busRouteStationList")){
                                busStop= new BusStop_item();
                            }

                            if (parser.getName().equals("stationId")) b_stationId = true;
                            if (parser.getName().equals("stationName")) b_stationName = true;
                            break;
                        case XmlPullParser.TEXT:
                            if (b_stationName) {
                                busStop.setStationName(parser.getText());
                                b_stationName = false;
                            } else if(b_stationId) {
                                busStop.setStationId(parser.getText());
                                b_stationId = false;
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //어답터 연결
            MyAdapter2 adapter = new MyAdapter2(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
        }
    }
}


