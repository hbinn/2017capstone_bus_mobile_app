package com.example.absin.firebasebus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class alarm_MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> itemList = new ArrayList<>();

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
    public View getView(int position, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_custom, viewGroup, false);
        }

        Switch swOnOff = (Switch) view.findViewById(R.id.swOnOff);
        TextView txtStartTime = (TextView) view.findViewById(R.id.txtStartTime);
        TextView txtEndTime = (TextView) view.findViewById(R.id.txtEndTime);
        TextView txtDays = (TextView) view.findViewById(R.id.txtDays);
        TextView txtBusNum = (TextView) view.findViewById(R.id.txtBusNum);
        TextView txtOnStop = (TextView) view.findViewById(R.id.txtOnStop);
        TextView txtOffStop = (TextView) view.findViewById(R.id.txtOffStop);

        MyItem myItem = itemList.get(position);

        swOnOff.setChecked(true);
        txtStartTime.setText(myItem.getStartTime());
        txtEndTime.setText(myItem.getEndTime());
        txtDays.setText(myItem.getDays());
        txtBusNum.setText(myItem.getBus_number());
        txtOnStop.setText(myItem.getBus_station());

        return view;
    }

    public void addItem(String days, String startTime, String endTime,
                        int gapTime, String bus_number, String bus_station) {
        MyItem mItem = new MyItem();

        //mItem.setWeek(week);
        mItem.setDays(days);
        mItem.setStartTime(startTime);
        mItem.setEndTime(endTime);
        mItem.setGapTime(gapTime);
        mItem.setBus_number(bus_number);
        mItem.setBus_station(bus_station);

        itemList.add(mItem);
    }
}