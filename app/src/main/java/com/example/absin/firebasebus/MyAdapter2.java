package com.example.absin.firebasebus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

class BusStop_item {

    String stationId;
    String stationName;


    public String getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }



    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private ArrayList<BusStop_item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyAdapter2(Context context, ArrayList<BusStop_item> items) {
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.busstop_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.tv_stationId.setText(mList.get(position).stationId);
        holder.tv_stationName.setText(mList.get(position).stationName);


        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_stationName;
        public TextView tv_stationId;
        public MyViewHolder(View itemView) {
            super(itemView);


            tv_stationName = itemView.findViewById(R.id.tv_stationName);
            tv_stationId = itemView.findViewById(R.id.tv_stationId);

        }
    }

}


