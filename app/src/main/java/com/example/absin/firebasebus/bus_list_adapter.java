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

class bus_list_item { // 해당 정류장을 지나는 모든 버스 출력

    String routeId; //버스 아이디
    String routeName; //버스 번호

    String predictTime1="도착정보없음";
    String predictTime2="도착정보없음";

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

    public String getrouteId() {
        return routeId;
    }
    public void setrouteId(String routeId){
        this.routeId = routeId;
    }
    public String getrouteName() {
        return routeName;
    }
    public void setrouteName(String routeName){
        this.routeName = routeName;
    }
}
public class bus_list_adapter extends RecyclerView.Adapter<bus_list_adapter.MyViewHolder>{
    private ArrayList<bus_list_item> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    View view;

    public bus_list_adapter(Context context, ArrayList<bus_list_item>items){
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
        holder.tv_routeName.setText(mList.get(position).routeName);
        holder.tv_predictTime1.setText(mList.get(position).predictTime1);
        holder.tv_predictTime2.setText(mList.get(position).predictTime2);


        //Click event
    }
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_routeName;
        public TextView tv_predictTime1;
        public TextView tv_predictTime2;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_routeName = itemView.findViewById(R.id.tv_routeName);
            tv_predictTime1 = itemView.findViewById(R.id.tv_predict1);
            tv_predictTime2 = itemView.findViewById(R.id.tv_predict2);
        }
    }
}