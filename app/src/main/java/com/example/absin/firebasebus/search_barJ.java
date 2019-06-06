package com.example.absin.firebasebus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class search_barJ extends AppCompatActivity {

    final String TAG = "search_barJ";
    EditText et1, et4;
    String keyword, keyword2;
    private String fileName = "star.list";

    public String dataKey = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl;
    ArrayList<Item> list = null;
    Item bus = null;
    RecyclerView recyclerView;
    LinearLayout imgDefault;

    public String dataKey2 = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl2;
    ArrayList<BusStop_item> list2 = null;
    BusStop_item busStop = null;
    RecyclerView recyclerView2;
    int check=1; //seach_barJ가 어디서 불려서 실행되었는지 구분하기 위해서

    MyAdapter adapter;
    public static Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bar);
        mContext = this;

        TabHost tabHost = (TabHost)findViewById(R.id.th);
        tabHost.setup();

        TabHost.TabSpec tabSpecBus = tabHost.newTabSpec("BUS").setIndicator("버스");
        tabSpecBus.setContent(R.id.bus);
        et1 = (EditText) findViewById(R.id.et2);
        tabHost.addTab(tabSpecBus);

        TabHost.TabSpec tabSpecBusStop = tabHost.newTabSpec("BUSSTOP").setIndicator("정류장");
        tabSpecBusStop.setContent(R.id.busstop);
        et4 = (EditText) findViewById(R.id.et2);

        tabHost.addTab(tabSpecBusStop);

        Intent intent = getIntent();
        check = intent.getIntExtra("Checksum", 0);

        if(check==2){
            tabHost.setCurrentTab(1);

        }else {
            tabHost.setCurrentTab(0);
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equalsIgnoreCase("BUSSTOP")) {
                    et4.setHint("정류장 입력");
                }
                else if (s.equalsIgnoreCase("BUS")) {
                    et1.setHint("버스 입력");
                }
            }
        });


        //et1 = (EditText) findViewById(R.id.et2);
        //et4 = (EditText) findViewById(R.id.et3);
        //et4 = (EditText) findViewById(R.id.et2);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView2.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);

        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
                recyclerView.removeAllViewsInLayout();
                keyword = et1.getText().toString();
                //  recyclerView.setAdapter(adapter);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }


            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // 입력하기 전에

            }

        });

        et4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때

                recyclerView2.removeAllViewsInLayout();
                keyword2 = et4.getText().toString();
                //  recyclerView.setAdapter(adapter);
                MyAsyncTask2 myAsyncTask2 = new MyAsyncTask2();
                myAsyncTask2.execute();
            }


            public void afterTextChanged(Editable arg0) {

                // 입력이 끝났을 때

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // 입력하기 전에

            }

        });
        /*et4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때

                recyclerView2.removeAllViewsInLayout();
                keyword2 = et4.getText().toString();
                //  recyclerView.setAdapter(adapter);
                MyAsyncTask myAsyncTask2 = new MyAsyncTask();
                myAsyncTask2.execute();
            }


            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에

            }

        });
        */

    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        public String getKeyword() {
            return keyword;
        }
        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapi.gbis.go.kr/ws/rest/busrouteservice?serviceKey=" +dataKey+ "&keyword=" + getKeyword();
            try {
                boolean b_routeName = false;
                boolean b_districtCd =false;
                boolean b_routeTypeName = false;
                boolean b_regionName = false;
                boolean b_routeId = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));


                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<Item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("busRouteList") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("busRouteList")){
                                bus = new Item();
                            }
                            if (parser.getName().equals("districtCd")) b_districtCd = true;
                            if(parser.getName().equals("regionName")) b_regionName = true;
                            if(parser.getName().equals("routeId")) b_routeId = true;
                            if (parser.getName().equals("routeName")) b_routeName = true;
                            if (parser.getName().equals("routeTypeName")) b_routeTypeName = true;

                            break;
                        case XmlPullParser.TEXT:
                            if(b_districtCd){
                                bus.setDistrictCd(parser.getText());
                                b_districtCd = false;
                            }else if(b_regionName) {
                                bus.setRegionName(parser.getText());
                                b_regionName = false;
                            }else if(b_routeId) {
                                bus.setRouteId(parser.getText());
                                b_routeId = false;
                            } else if(b_routeName) {
                                bus.setRouteName(parser.getText());
                                b_routeName = false;
                            } else if (b_routeTypeName) {
                                bus.setRouteTypeName(parser.getText());
                                b_routeTypeName = false;
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
            String selectedBus;

            //어답터 연결
            adapter = new MyAdapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);

            if (adapter.isStarClicked) {
                //Toast.makeText(getApplicationContext(), "저장하자", Toast.LENGTH_SHORT).show();
                //selectedBus = adapter.getSelectedBus();
                //putFile(selectedBus);
            }

            imgDefault = (LinearLayout) findViewById(R.id.imgDefault);
            if (adapter.getItemCount() > 0) {
                imgDefault.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            else {
                imgDefault.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }

    public class MyAsyncTask2 extends AsyncTask<String, Void, String> {

        public String getKeyword() {
            return keyword2;
        }

        @Override
        protected String doInBackground(String... strings) {

            requestUrl2 = "http://openapi.gbis.go.kr/ws/rest/busstationservice?serviceKey=" + dataKey2 + "&keyword=" +getKeyword();
            try {

                boolean b_stationId =false;
                boolean b_stationName = false;

                URL url2 = new URL(requestUrl2);
                InputStream is2 = url2.openStream();
                XmlPullParserFactory factory2 = XmlPullParserFactory.newInstance();
                XmlPullParser parser2 = factory2.newPullParser();
                parser2.setInput(new InputStreamReader(is2, "UTF-8")); //input stream으로부터 데이터 입력받기

                String tag;
                int eventType2 = parser2.getEventType();

                while(eventType2 != XmlPullParser.END_DOCUMENT){
                    switch (eventType2){
                        case XmlPullParser.START_DOCUMENT: //파싱 시작 단계
                            list2 = new ArrayList<BusStop_item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser2.getName().equals("busStationList") && busStop != null) {
                                list2.add(busStop);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser2.getName().equals("busStationList")){
                                busStop= new BusStop_item();
                            }

                            if (parser2.getName().equals("stationId")) b_stationId = true;
                            if (parser2.getName().equals("stationName")) b_stationName = true;
                            break;
                        case XmlPullParser.TEXT:
                            if (b_stationName) {
                                busStop.setStationName(parser2.getText());
                                b_stationName = false;
                            } else if(b_stationId) {
                                busStop.setStationId(parser2.getText());
                                b_stationId = false;
                            }
                            break;
                    }
                    eventType2 = parser2.next();
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
            MyAdapter2 adapter = new MyAdapter2(getApplicationContext(), list2);
            recyclerView2.setAdapter(adapter);
        }
    }

    public void putFile(ArrayList<String> selectedBuses) {
        File file = new File(getFilesDir(), fileName);
        FileWriter fw = null;
        BufferedWriter bufwr = null;

        try {
            fw = new FileWriter(file);
            bufwr = new BufferedWriter(fw);

//            for (int i=0; i<adapter.getCount(); i++) {
//                bufwr.write(adapter.getItem(i).toString());
//                bufwr.newLine();
//            }

            for (int i=0; i<selectedBuses.size(); i++) {
                bufwr.write(selectedBuses.get(i));
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
}