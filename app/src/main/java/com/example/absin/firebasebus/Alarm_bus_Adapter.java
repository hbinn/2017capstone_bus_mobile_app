package com.example.absin.firebasebus;

/**
 * Created by absin on 2019-05-28.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by absin on 2019-05-17.
 */


public class Alarm_bus_Adapter extends RecyclerView.Adapter<Alarm_bus_Adapter.MyViewHolder> {

    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    public Activity mActivity;



    public Alarm_bus_Adapter(Activity mActivity, Context context, ArrayList<Item> itmes) {

        this.mActivity = mActivity;
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view, mActivity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Alarm_bus_Adapter.MyViewHolder holder, final int position) {

            holder.tv_routeName.setText(mList.get(position).routeName);
            String sub = mList.get(position).districtCd;


            //holder.tv_routeName.setTextColor(Color.rgb(178, 204, 255));



            String region = mList.get(position).regionName;
            String region2[] = region.split(",");

            if(region2[0].equals("서울")) {
                holder.tv_routeName.setTextColor(Color.rgb(255, 69, 19));
            }else {
                holder.tv_routeName.setTextColor(Color.rgb(19, 164, 225));
            }

            holder.tv_regionName.setText(region2[0]);
            holder.tv_routeTypeName.setText(mList.get(position).routeTypeName);

            //Click event

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String result[] = {mList.get(position).routeName, mList.get(position).routeId};
                intent.putExtra("RouteId", result);
                         mActivity.setResult(RESULT_OK, intent);

                //액티비티(팝업) 닫기
                mActivity.finish();


            }
        });

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_routeName;
        public TextView tv_regionName;
        public TextView tv_routeTypeName;
        public Activity mActivity;

        public MyViewHolder(View itemView, Activity mActivity) {
            super(itemView);

            tv_routeName = itemView.findViewById(R.id.tv_routename);
            tv_regionName = itemView.findViewById(R.id.tv_regionname);
            tv_routeTypeName = itemView.findViewById(R.id.tv_routetypename);
            this.mActivity = mActivity;
        }
    }

}
