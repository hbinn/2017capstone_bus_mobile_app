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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by absin on 2019-05-17.
 */



public class Alarm_station_Adapter extends RecyclerView.Adapter<Alarm_station_Adapter.MyViewHolder> {

    private ArrayList<station_after> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    View view;

    public Activity mActivity;

    public Alarm_station_Adapter(Activity mActivity, Context context, ArrayList<station_after> itmes) {

        this.mActivity = mActivity;
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = mInflate.inflate(R.layout.san_iten, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view, mActivity);
        return viewHolder;
    }

    @Override
    //뷰에 값이 채워질 3개를 여기서 하는 것
    public void onBindViewHolder(Alarm_station_Adapter.MyViewHolder holder, final int position) {

        holder.tv_stationName.setText(mList.get(position).stationName);


        if(mList.get(position).tracker==1){
            holder.tv_buspointer.setVisibility(View.VISIBLE);
            holder.tv_stationName.setTextColor(Color.BLUE);
        }else {
            holder.tv_buspointer.setVisibility(View.INVISIBLE);
            holder.tv_stationName.setTextColor(Color.BLACK);
        }
        //Click event

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String result[] = {mList.get(position).stationName, mList.get(position).stationId};
                intent.putExtra("StationId", result);
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
        public TextView tv_stationName;
        public ImageView tv_buspointer;
        public Activity mActivity;

        public MyViewHolder(View itemView, Activity mActivity) {
            super(itemView);

            tv_stationName = itemView.findViewById(R.id.tv_stationname2);
            tv_buspointer = itemView.findViewById(R.id.tv_image);
            this.mActivity = mActivity;

        }
    }

}