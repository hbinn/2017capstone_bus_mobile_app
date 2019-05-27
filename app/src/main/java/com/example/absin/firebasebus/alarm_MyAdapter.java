package com.example.absin.firebasebus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class alarm_MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> myItems = new ArrayList<>();

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public Object getItem(int i) {
        return myItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_custom, viewGroup, false);
        }

        TextView busTxt = (TextView) view.findViewById(R.id.busNum);
        TextView onStopTxt = (TextView) view.findViewById(R.id.onStop);
        TextView offStopTxt = (TextView) view.findViewById(R.id.offStop);
        TextView dayTxt = (TextView) view.findViewById(R.id.day);
        TextView timeTxt = (TextView) view.findViewById(R.id.time);
        TextView gapTimeTxt = (TextView) view.findViewById(R.id.gapTime);

        return view;
    }

    public void addItem(int bus, int onStop, int offStop, String day, int time, int gapTime) {
        MyItem mItem = new MyItem();

        mItem.setBus(bus);
        mItem.setonStop(onStop);
        mItem.setOffStop(offStop);
        mItem.setDay(day);
        mItem.setGapTime(gapTime);

        myItems.add(mItem);
    }
}