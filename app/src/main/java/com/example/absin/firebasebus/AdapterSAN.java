package com.example.absin.firebasebus;

import android.content.Context;
import android.content.Intent;
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

/**
 * Created by absin on 2019-05-17.
 */

class station_after {  //노선이 가지고 있는 정류장을 모두 출력하기 위해 만든 클래스
    String stationId; //정류소 아이디
    String mobileNo; //정류소 번호
    String stationName;
    int tracker =0;


    public String getStationId() { return stationId; }
    public String getMobileNo() { return mobileNo; }
    public String getStationName() { return stationName; }

    public int getTracker() { return tracker; }


    public void setStationId(String stationId) { this.stationId = stationId; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public void setTracker(int tracker) {this.tracker = tracker; }
}

class station_tracker { //이 클래스의 용도에 대한 자세한 설명은 Station_After_Number의 MySyncTask3 class 바로 위에 작성해두었다.
    String stationId;

    public String getStaionId() { return stationId;}

    public void setStationId(String stationId) {this.stationId= stationId;}
}


public class AdapterSAN extends RecyclerView.Adapter<AdapterSAN.MyViewHolder> {

    private ArrayList<station_after> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    View view;

    public AdapterSAN(Context context, ArrayList<station_after> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         view = mInflate.inflate(R.layout.san_iten, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    //뷰에 값이 채워질 3개를 여기서 하는 것
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

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
                Context context = view.getContext();
                Intent intent = new Intent(context, bus_list.class);
                intent.putExtra("stationId", mList.get(position).stationId);
                intent.putExtra("stationName", mList.get(position).stationName);
                context.startActivity(intent);
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


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_stationName = itemView.findViewById(R.id.tv_stationname2);
            tv_buspointer = itemView.findViewById(R.id.tv_image);

        }
    }

}