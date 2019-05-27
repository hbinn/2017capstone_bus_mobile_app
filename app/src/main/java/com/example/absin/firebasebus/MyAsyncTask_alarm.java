package com.example.absin.firebasebus;

import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by absin on 2019-05-23.
 */


public class MyAsyncTask_alarm extends AsyncTask<String, Void, String> {


    final String TAG = "MainActivity";
    public String dataKey = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
    private String requestUrl;
    ArrivedBus arrival = new ArrivedBus();
    String keyword1, keyword2;
    //stationId, routeId, staOrder
    private Context mContext;



    public MyAsyncTask_alarm(Context context, String keyword1, String keyword2) {

        mContext = context;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;

    }

    @Override
    protected String doInBackground(String... strings) {


        requestUrl = "http://openapi.gbis.go.kr/ws/rest/busarrivalservice?serviceKey=" + dataKey + "&stationId=" + keyword1 + "&routeId=" + keyword2;


        try {
            boolean b_predictTime1 = false;
            boolean b_predictTime2 = false;
            boolean b_routeId = false;
            boolean b_stationId = false;


            URL url = new URL(requestUrl);
            InputStream is = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(is, "UTF-8"));


            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("busArrivalItem")) {

                        }
                        if (parser.getName().equals("predictTime1")) b_predictTime1 = true;
                        if (parser.getName().equals("predictTime2")) b_predictTime2 = true;
                        if (parser.getName().equals("routeId")) b_routeId = true;
                        if (parser.getName().equals("stationId")) b_stationId = true;

                        break;
                    case XmlPullParser.TEXT:
                        if (b_predictTime1) {
                            arrival.setPredictTime1(parser.getText());
                            b_predictTime1 = false;
                        } else if (b_predictTime2) {
                            arrival.setPredictTime2(parser.getText());
                            b_predictTime2 = false;
                        } else if (b_routeId) {
                            arrival.setRouteId(parser.getText());
                            b_routeId = false;
                        } else if (b_stationId) {
                            arrival.setStationId(parser.getText());
                            b_stationId = false;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String value = arrival.predictTime1 +"," + arrival.predictTime2;

        return value;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);


    }
}
