package com.example.absin.firebasebus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*텍스트 뷰에 줄 넣으려고*/
        String sitename = "버스/정류장 검색을 위해 클릭해주세요";
        TextView textView = (TextView) findViewById(R.id.main_search);
        textView.setText(Html.fromHtml("<u>" + sitename + "</u>"));



    }
}
