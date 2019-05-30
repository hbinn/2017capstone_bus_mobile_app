package com.example.absin.firebasebus;

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
class bus_time{
    String predictTime1;
    String predictTime2;

    public String getPredictTime1(){
        return predictTime1;
    }
    public void setPredictTime1(String predictTime1){
        this.predictTime1 = predictTime1;
    }

    public String getPredictTime2(){
        return predictTime2;
    }
    public void setPredictTime2(String predictTime2){
        this.predictTime2 = predictTime2;
    }
}

public class time_adapter extends RecyclerView.Adapter<time_adapter.MyViewHolder>{
    private ArrayList<bus_time> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    View view;

    public time_adapter(Context context, ArrayList<bus_time>items){
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = mInflate.inflate(R.layout.bus_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_predict1.setText(mList.get(position).predictTime1);
        holder.tv_predict2.setText(mList.get(position).predictTime2);

        //Click event
    }
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_predict1;
        public TextView tv_predict2;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_predict1 = itemView.findViewById(R.id.tv_predict1);
            tv_predict2 = itemView.findViewById(R.id.tv_predict2);
        }
    }
}