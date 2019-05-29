
package com.example.absin.firebasebus;
 import android.content.Intent;
 import android.provider.ContactsContract;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.text.Html;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.io.BufferedReader;
        import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*텍스트 뷰에 줄 넣으려고*/
        TextView textView = (TextView) findViewById(R.id.main_search);
        LinearLayout go_search = (LinearLayout) findViewById(R.id.go_to_search); //검색 화면으로 가기 위해서 이 화면을 클릭해야한다

        final LinearLayout main_map = (LinearLayout) findViewById(R.id.main_tap_map); //final은 내부 클래스에서 동작을 해야할때 써야된다는데 혹시 어떻게 될지 몰라서 써두었다.
        final LinearLayout main_home = (LinearLayout) findViewById(R.id.main_tap_home);
        final LinearLayout main_memo = (LinearLayout) findViewById(R.id.main_tap_secretary); //메모장
        final LinearLayout main_alarm = (LinearLayout) findViewById(R.id.main_tap_alarm);

//        String sitename = "검색창 수정예정";
//        textView.setText(Html.fromHtml("<u>" + sitename + "</u>"));


        //검색창을 누르면 검색 화면으로 넘어가는 코드
        go_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), search_barJ.class);
                startActivity(intent);
            }
        });

        //텍스트 부분 누르면 안 넘어가져서 넘어가게 따로 만듬.
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), search_barJ.class);
                startActivity(intent);
            }
        });

        //메모 이동
        main_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), memo.class);
                startActivity(intent);
            }
        });

        //메인 화면에 저장한 메모 뜨게 하기 (새로고침이 필요할듯)
        TextFileManager mTextFileManager = new TextFileManager(MainActivity.this);
        String memoData = mTextFileManager.load(); //저장한 데이터를 불러온다
        TextView mMemo = (TextView) findViewById(R.id.main_memo);
        mMemo.setText(memoData); //해당 데이터를 메인화면에 뜨게 한다


        main_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여긴 클릭을 하면 아예 파란색으로 바꾸는 걸로 해봤는데 생각보다 촌스러워서 그림자 지는게 더 좋을것 같다.

                /*문제: 이 버튼을 파란색으로 만들었으면
                1) 다른 버튼을 파란색이 아니게 바꿔주거나
                2) (메인바가 표기되는 화면으로 넘어갈경우) 거기서 파란버튼으로 바꿔주는 걸 호출해야함
                -->안그러면 이미 예전에 눌렀던 버튼 모두가 파란색으로 되어있음
                --->넘어갈때는 그림자 이펙트만 해놓고, 액티비티가 넘어가면 거기서 해당 버튼을 파란색으로 만드는게 좋을 것 같다.
                --->그리고 그 액티비티 끝날때 해당 버튼 색 원래색으로 바꿔주기
                * */

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        main_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(intent);
            }
        });


    }
}