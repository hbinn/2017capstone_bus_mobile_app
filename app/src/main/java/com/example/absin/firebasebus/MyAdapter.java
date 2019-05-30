package com.example.absin.firebasebus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


import static android.content.Context.MODE_PRIVATE;


/**
 * Created by absin on 2019-05-17.
 */

class Item {

    String routeName;
    String districtCd;
    String routeTypeName;
    String regionName;
    String routeId;


    public String getRouteName() {
        return routeName;
    }

    public String getDistrictCd() {
        return districtCd;
    }

    public String getRouteTypeName() {
        return routeTypeName;
    }

    public String getRegionName() {return regionName; }

    public String getRouteId() { return  routeId; }



    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setDistrictCd(String districtCd) {
        this.districtCd = districtCd;
    }

    public void setRouteTypeName(String routeTypeName) {
        this.routeTypeName = routeTypeName;
    }

    public void setRegionName(String regionName) { this.regionName = regionName; }

    public void setRouteId(String routeId) { this.routeId = routeId; }


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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //binding
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
                Context context = view.getContext();
                Intent intent = new Intent(context, Station_After_Number.class);
                intent.putExtra("RouteId", mList.get(position).routeId);
                context.startActivity(intent);
            }
        });

        holder.star.setOnClickListener(new View.OnClickListener(){//즐겨찾기 토글버튼 클릭
            @Override
            public void onClick(View view) {
                if(holder.star.isChecked()){//체크되면
                    holder.star.setChecked(true);
                    Toast.makeText(mContext, "즐겨찾기에 저장되었습니다.", Toast.LENGTH_LONG).show();
                    Context context = view.getContext();
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.putExtra("RouteName", mList.get(position).routeName);

                }
                else{//체크 사라지면
                    holder.star.setChecked(false);
                    Toast.makeText(mContext, "즐겨찾기에 삭제되었습니다.", Toast.LENGTH_LONG).show();

                }
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
        public ToggleButton star;


        public MyViewHolder(View itemView) {
            super(itemView);


            tv_routeName = itemView.findViewById(R.id.tv_routename);
            tv_regionName = itemView.findViewById(R.id.tv_regionname);
            tv_routeTypeName = itemView.findViewById(R.id.tv_routetypename);
            star = (ToggleButton) itemView.findViewById(R.id.star);




        }


    }

}