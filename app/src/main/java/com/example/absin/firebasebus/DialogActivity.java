package com.example.absin.firebasebus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zagum.switchicon.SwitchIconView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by absin on 2019-05-24.
 */

public class DialogActivity extends AppCompatActivity {


    TextView timeView;
    View get_bus;
    View not_get_bus;
    SwitchIconView switchIcon1;
    SwitchIconView switchIcon2;
    TextView pTime1;
    TextView pTime2;
    TextView bus_number;

    TextView weather_text;
    ImageView weather_image;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_dialog);

        switchIcon1 = (SwitchIconView) findViewById(R.id.switchIconView1);
        switchIcon2 = (SwitchIconView) findViewById(R.id.switchIconView2);
        timeView = (TextView) findViewById(R.id.get_time);
        get_bus = (View) findViewById(R.id.get_bus);
        not_get_bus = (View) findViewById(R.id.not_get_bus);
        pTime1 = (TextView) findViewById(R.id.pTime1);
        pTime2 = (TextView) findViewById(R.id.pTime2);
        bus_number = (TextView) findViewById(R.id.bus_number);

        weather_text = (TextView) findViewById(R.id.weather_text);
        weather_image = (ImageView) findViewById(R.id.weather_image);

        MyAsyncTask_weather weatherTask = new MyAsyncTask_weather();
        weatherTask.execute();


        Intent intent = getIntent();
        String result = intent.getStringExtra("predict");
        String result2[] = result.split(",");
        String busNumber = intent.getStringExtra("BusNumber");

        bus_number.setText(busNumber);
        pTime1.setText("1번 버스: "+ result2[0] +"분 전");
        pTime2.setText("2번 버스: " +result2[1]+"분 전");
        //알람이 오면 소리 나게 하는 부분
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //가내수공업 해서 무슨 소리 나나 해보기
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();

        //진동
        vibrate();


        Calendar time_cal = Calendar.getInstance(Locale.getDefault());
        int hour = time_cal.get(Calendar.HOUR_OF_DAY);
        int minute = time_cal.get(Calendar.MINUTE);
        String minute_sub = Integer.toString(minute);

        if(minute_sub.length()==1) {
            minute_sub = "0"+ minute_sub;
        }


        //현재 시간 받아서 날짜 표시
        timeView.setText(Integer.toString(hour) + " : " + minute_sub);

        //화면이 꺼져있거나 잠겨있을때 다 무시하고 알람표시
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);


        //리시버2를 인자로 걸로 캔슬을 하는게 맞았다. 2번째 알람매니저가 AlarmReceiver에 있기는 했는데 실제로 이 알람을 듣고있는 애는 Receiver2여서 얘를 취소시켜줘야 된다.

        Intent intent2 = new Intent(this, Recevier2.class);

//        String strEndTime = intent.getStringExtra("endTime");
//        String[] arrEndTime = intent.getStringExtra("endTime").split(" ");
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrEndTime[0]));
//        endTime.set(Calendar.MINUTE, Integer.parseInt(arrEndTime[1]));

        int REQCODE2 = intent.getIntExtra("REQCODE2", -1);
        //final PendingIntent sender = PendingIntent.getBroadcast(this, 100, intent2, 0);
        final PendingIntent sender = PendingIntent.getBroadcast(this, REQCODE2, intent2, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

//        if (Calendar.getInstance().after(endTime)) {
//            alarmManager.cancel(sender);
//        }

        get_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIcon1.switchState();
                alarmManager.cancel(sender);
                finish();
            }
        });


        not_get_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIcon2.switchState();
                finish();
                //딱히 하는 일은 없고 그냥 dialog 창을 없애주기만 한다.
            }
        });

    }


    public void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // Vibrate for 500 milliseconds only
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500); // deprecated in API 26
        }
    }


    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }

    public class MyAsyncTask_weather extends AsyncTask<String, Void, Document> {

        String base_time;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date date = new Date();
        String weather_date = formatter.format(date);  //현재 년,월,일 받아오기
        String key = "vgOxwLDnBL1K%2B0EV%2FG7Yi%2Bge%2BwfXMB66UwEnnmJEUuoej7Zg75Z85lE7wOcYZcysMUq5Sa2VGKzNsczJqzgg9A%3D%3D";
        String weather_url;
        //위도와 경도는 경기대학교가 있는 원천동으로 나오게 했고, 시간은 새벽 5시에 관측된 예보를 기준으로 가져오게 함


        @Override
        protected Document doInBackground(String... urls) {

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);

//            switch (hour) {
//                case 2:case 3:case 4:
//                    base_time = "0200";
//                    break;
//                case 5:case 6:case 7:
//                    base_time = "0500";
//                    break;
//                case 8:case 9:case 10:
//                    base_time= "0800";
//                    break;
//                case 11:case 12:case 13:
//                    base_time = "1000";
//                    break;
//                case 14:case 15:case 16:
//                    base_time = "1400";
//                    break;
//                case 17:case 18:case 19:
//                    base_time="1700";
//                    break;
//                case 20:case 21:case 22:
//                    base_time="2000";
//                    break;
//                case 23:case 24:case 1:
//                    base_time="2300";
//                    break;
//                default:
//                    base_time = "0200";
//                    break;
//            }

            base_time= "0500";
            weather_url= "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey="+key + "&base_date=" + weather_date + "&base_time="+base_time+"&nx=61&ny=120&numOfRows=10&_type=xml";

            URL url;
            Document doc = null;

            try {
                url = new URL(weather_url);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
              //  Toast.makeText(context, "Parsing Error", Toast.LENGTH_SHORT).show();

            }
            return doc;

        }



        @Override
        protected void onPostExecute(Document doc) {

            String s="";
            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;

                NodeList idx = fstElmnt.getElementsByTagName("category");


                // 구름상태 SKY, fcstValue 구름상태에 해당하는 값
                // 0~2 : 맑음, 3~5 : 구름조금, 6~8 : 구름많음, 9~10 : 흐림

                if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("SKY")) {

                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");

                    int cloud_num = Integer.parseInt(gugun.item(0).getChildNodes().item(0).getNodeValue());


                    if (cloud_num == 0 || cloud_num == 1 || cloud_num == 2) {
                        s += "맑음,";
                    } else if (cloud_num == 3 || cloud_num == 4 || cloud_num == 5) {
                        s += "구름 조금,";
                    } else if (cloud_num == 6 || cloud_num == 7 || cloud_num == 8) {
                        s += "구름 많음,";
                    } else if (cloud_num == 9 || cloud_num == 10) {
                        s += "흐림,";

                    }

                }


                // 3시간 동안의 기온
                if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T3H")) {

                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");

                    s +=  gugun.item(0).getChildNodes().item(0).getNodeValue() ;

                }



            }

            String sub[] = s.split(",");

            if(sub[0].equals("맑음")) {
                weather_image.setImageResource(R.drawable.iconfinder_weather01_4102328);
            }else if(sub[0].equals("구름 조금")) {
                weather_image.setImageResource(R.drawable.iconfinder_weather02_4102326);
            }else if(sub[0].equals("구름 많음")) {
                weather_image.setImageResource(R.drawable.iconfinder_weather04_4102315);
            }else {
                weather_image.setImageResource(R.drawable.iconfinder_weather07_4102320);
            }

            weather_text.setText(sub[1] +"℃");

            super.onPostExecute(doc);
        }

    }



}
