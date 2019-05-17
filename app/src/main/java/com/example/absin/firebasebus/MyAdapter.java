package com.example.absin.firebasebus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by absin on 2019-05-17.
 */

class Item {

    String routeName;
    String districtCd;
    String routeTypeName;



    public String getRouteName() {
        return routeName;
    }

    public String getDistrictCd() {
        return districtCd;
    }

    public String getRouteTypeName() {
        return routeTypeName;
    }



    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setDistrictCd(String districtCd) {
        this.districtCd = districtCd;
    }

    public void setRouteTypeName(String routeTypeName) {
        this.routeTypeName = routeTypeName;
    }

}


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<Item> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    //뷰에 값이 채워질 3개를 여기서 하는 것
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.tv_routeName.setText(mList.get(position).routeName);

        holder.tv_districtCd.setText(mList.get(position).districtCd);
            if(holder.tv_districtCd.equals("1")) {
                holder.tv_districtCd.setText("서울");

            }else if(holder.tv_districtCd.equals("2")){
                holder.tv_districtCd.setText("경기");

            }else {
                holder.tv_districtCd.setText("인천");
            }

        holder.tv_routeTypeName.setText(mList.get(position).routeTypeName);

        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_routeName;
        public TextView tv_districtCd;
        public TextView tv_routeTypeName;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_routeName = itemView.findViewById(R.id.tv_routename);
            tv_districtCd = itemView.findViewById(R.id.tv_districtCd);
            tv_routeTypeName = itemView.findViewById(R.id.tv_routetypename);

        }
    }

}