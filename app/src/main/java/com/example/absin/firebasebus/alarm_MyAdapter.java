package com.example.absin.firebasebus;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class alarm_MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> itemList = new ArrayList<>();
    private boolean isClicked = false;
    private Activity parentActivity;
    Context context;

    int check_first= 1; // 처음으로 알람을 셋팅할때는 자동으로 alarmmanager가 생성이 되니까 나중에 꺼졌다가 킬때를 구분하기 위해

    public alarm_MyAdapter(Context context, Activity parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_custom, viewGroup, false);
        }

        Button btnDelete = (Button) view.findViewById(R.id.btnDelete);
        Switch swOnOff = (Switch) view.findViewById(R.id.swOnOff);
        TextView txtStartTime = (TextView) view.findViewById(R.id.txtStartTime);
        TextView txtEndTime = (TextView) view.findViewById(R.id.txtEndTime);
        TextView txtGapTime = (TextView) view.findViewById(R.id.txtGapTime);
        TextView txtDays = (TextView) view.findViewById(R.id.txtDays);
        TextView txtBusNum = (TextView) view.findViewById(R.id.txtBusNum);
        TextView txtOnStop = (TextView) view.findViewById(R.id.txtOnStop);
        TextView txtOffStop = (TextView) view.findViewById(R.id.txtOffStop);

        final MyItem myItem = itemList.get(position);

        if (isClicked) {
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
        }
        //swOnOff.setChecked(true);

        if(myItem.getOn_off().equals("on")){
            swOnOff.setChecked(true);
        }else {
            swOnOff.setChecked(false);
        }

        String[] strTime = myItem.getStartTime().split(" ");
        String[] endTime = myItem.getEndTime().split(" ");
        txtStartTime.setText(strTime[0] + "시 " + strTime[1] + "분");
        txtEndTime.setText(endTime[0] + "시 " + endTime[1] + "분");

        txtGapTime.setText("도착  " + myItem.getGapTime() + " 분 전");
        txtDays.setText(myItem.getDays());
        txtBusNum.setText(myItem.getBus_number());
        txtOnStop.setText(myItem.getBus_station());



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
                builder.setMessage("알람을 삭제하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                System.out.println(myItem.getREQCODE1() +" "+ myItem.getREQCODE2() +" "+myItem.getBus_number()+" "+myItem.getBus_station()+ myItem.getGapTime());
                                itemList.remove(position);
                                notifyDataSetChanged();
                                ((AlarmActivity) context).cancelAlarm(Integer.parseInt(myItem.getREQCODE1()),
                                                                        Integer.parseInt(myItem.getREQCODE2()));
                                Toast.makeText(context, "알람이 삭제되었습니다. 뒤로가기를 눌러주세요", Toast.LENGTH_SHORT).show();
                                ((AlarmActivity) context).setLayout();

                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                builder.show();
            }
        });

        //추가
        swOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
//                    if(check_first==1) {
//                        //do nothing. 처음으로 실행된거라서 자동으로 알람이 설정되었다.
//                    }else {

                    boolean week[] = new boolean[8];
                    week[0]= false;
                    String sub_day = myItem.getDays();

                    if(sub_day.contains("일")) week[1]=true;
                    if(sub_day.contains("월")) week[2]=true;
                    if(sub_day.contains("화")) week[3]=true;
                    if(sub_day.contains("수")) week[4]=true;
                    if(sub_day.contains("목")) week[5]=true;
                    if(sub_day.contains("금")) week[6]=true;
                    if(sub_day.contains("토")) week[7]=true;

                    Intent intent = new Intent(context, AlarmReceiver.class);
                    intent.putExtra("weekday", week);
                    intent.putExtra("gapTime", myItem.getGapTime());
                    intent.putExtra("endTime", myItem.getEndTime());
                    intent.putExtra("endTime2", myItem.getEndTime());
                    intent.putExtra("RouteId", myItem.getBus_routeId());
                    intent.putExtra("BusNumber", myItem.getBus_number());
                    intent.putExtra("StationId", myItem.getBus_stationId());
                    intent.putExtra("REQCODE2", myItem.getREQCODE2());
                    PendingIntent pIntent = PendingIntent.getBroadcast(context, Integer.parseInt(myItem.getREQCODE1()), intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);

                    String start_sub[] = (myItem.getStartTime()).split(" ");
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(start_sub[0]));
                    cal.set(Calendar.MINUTE, Integer.parseInt(start_sub[1]));

                    long interval = 1000 * 60* 60 *24; //24시간
                    //alarmInfo.getAm().setRepeating(AlarmManager.RTC_WAKEUP, alarmInfo.getStartTime().getTimeInMillis(), interval, pIntent);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, pIntent);
                    Toast.makeText(context, "알람이 켜졌습니다.", Toast.LENGTH_SHORT).show();
                    myItem.setOn_off("on");
                    System.out.println("알람 다시 만들어짐");
                    System.out.println("시작 리퀘코드"+myItem.getREQCODE1() + " "+ myItem.getREQCODE2());
                    // }
                }else {
                   // check_first=2; //한번 꺼진거 표시

//                    Intent intent1 = new Intent(context,AlarmReceiver.class);
//                    Intent intent2 = new Intent(context, Recevier2.class);
//                    PendingIntent sender1 = PendingIntent.getBroadcast(context, Integer.parseInt(myItem.getREQCODE1()), intent1, 0);
//                    PendingIntent sender2 = PendingIntent.getBroadcast(context, Integer.parseInt(myItem.getREQCODE2()), intent2, 0);
//                    AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//                    am.cancel(sender1);
//                    am.cancel(sender2);

                    ((AlarmActivity) context).cancelAlarm(Integer.parseInt(myItem.getREQCODE1()),
                            Integer.parseInt(myItem.getREQCODE2()));

                    Toast.makeText(context, "알람이 꺼졌습니다.", Toast.LENGTH_SHORT).show();
                    myItem.setOn_off("off");
                    System.out.println("끝내기리퀘코드"+myItem.getREQCODE1() + " "+ myItem.getREQCODE2());
                }

                ((AlarmActivity) context).putFile();
                ((AlarmActivity) context).setLayout();
            }
        });

        return view;
    }

//    public void addItem(String REQCODE1, String REQCODE2, String days, String startTime, String endTime,
//                        String gapTime, String bus_number, String bus_station) {
    public void addItem(String REQCODE1, String REQCODE2, String days, String startTime, String endTime,
                        String gapTime, String bus_number, String bus_station, String bus_routeId, String bus_stationId, String on_off) {
        MyItem mItem = new MyItem();

        //mItem.setWeek(week);
        mItem.setREQCODE1(REQCODE1);
        mItem.setREQCODE2(REQCODE2);
        mItem.setDays(days);
        mItem.setStartTime(startTime);
        mItem.setEndTime(endTime);
        mItem.setGapTime(gapTime);
        mItem.setBus_number(bus_number);
        mItem.setBus_station(bus_station);
        //추가
        mItem.setBus_routeId(bus_routeId);
        mItem.setBus_stationId(bus_stationId);
        mItem.setOn_off(on_off);

        itemList.add(mItem);
    }

    public void addItem(String str) {
        MyItem mItem = new MyItem();

        String[] strArr = str.split(",");

        mItem.setREQCODE1(strArr[0]);
        mItem.setREQCODE2(strArr[1]);
        mItem.setDays(strArr[2]);
        mItem.setStartTime(strArr[3]);
        mItem.setEndTime(strArr[4]);
        mItem.setGapTime(strArr[5]);
        mItem.setBus_number(strArr[6]);
        mItem.setBus_station(strArr[7]);
        //추가
        mItem.setBus_routeId(strArr[8]);
        mItem.setBus_stationId(strArr[9]);
        mItem.setOn_off(strArr[10]);
        itemList.add(mItem);
    }

    public void editClick(boolean click) {
        isClicked = click;
        notifyDataSetChanged();
    }

    public boolean isEditClicked() {
        return isClicked;
    }


}