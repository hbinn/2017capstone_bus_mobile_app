package com.example.absin.firebasebus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junny_PC on 2019-06-05.
 */

class Myitem_star{

    String routeName;

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}

public class MyAdapter_star extends BaseAdapter {

    ArrayList<Myitem_star> sList = new ArrayList<>();
    Context context;
    Activity parentActivity;

    public MyAdapter_star(Context context, Activity parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }
    @Override
    public int getCount() {
        return sList.size();
    }

    @Override
    public Object getItem(int i) {
        return sList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_star, viewGroup, false);
        }


        TextView txtEndTime = (TextView) view.findViewById(R.id.BusName);


        final Myitem_star myitem = sList.get(position);
        txtEndTime.setText(myitem.getRouteName());



        return view;
    }

    public void addItem(String BusName1) {
        Myitem_star mItem = new Myitem_star();

        //mItem.setWeek(week);
        mItem.setRouteName(BusName1);

        sList.add(mItem);
    }
}
