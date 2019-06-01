package com.example.absin.firebasebus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class alarm_MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> itemList = new ArrayList<>();
    private boolean isClicked = false;
    private Activity parentActivity;
    Context context;

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
        swOnOff.setChecked(true);

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
                                itemList.remove(position);
                                notifyDataSetChanged();
//                                ((AlarmActivity) context).cancelAlarm(Integer.parseInt(myItem.getREQCODE1()),
//                                                                        Integer.parseInt(myItem.getREQCODE2()));
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

        return view;
    }

    public void addItem(String REQCODE1, String REQCODE2, String days, String startTime, String endTime,
                        String gapTime, String bus_number, String bus_station) {
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