package com.example.absin.firebasebus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Junny_PC on 2019-06-08.
 */

public class GetOffAlarm extends AppCompatActivity{
    double stationX;
    double stationY;

    Button btnAgree;
    Button btnDisAgree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getoff_dialog);

//        Intent intent = getIntent();
//        stationX = intent.getDoubleExtra("stationX", 0);
//        stationY = intent.getDoubleExtra("stationY", 0);
//
//
//        final ImageView movingbus = (ImageView) findViewById(R.id.movingbus);
//        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.moving_bus);
//        movingbus.startAnimation(anim);
//
//        btnAgree = (Button) findViewById(R.id.btnAgree);
//        btnDisAgree = (Button) findViewById(R.id.btnDisAgree);
//
//        btnAgree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(
//                        getApplicationContext(), GetOffService.class);
//                intent2.putExtra("latitude", stationX);
//                intent2.putExtra("longitude", stationY);
//                startService(intent2);
//                finish();
//            }
//        });
//
//        btnDisAgree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }
}
