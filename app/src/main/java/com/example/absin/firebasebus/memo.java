package com.example.absin.firebasebus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class memo extends AppCompatActivity {

    Button load, save, delete;
    EditText mMemoEdit = null;
    TextFileManager mTextFileManager = new TextFileManager(this);
  //  Button btn_load = (Button)findViewById(R.id.load);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);

        load= (Button) findViewById(R.id.load);
        save = (Button) findViewById(R.id.save);
        delete = (Button) findViewById(R.id.delete);

        load.setOnClickListener(listener);
        save.setOnClickListener(listener);
        delete.setOnClickListener(listener);
        mMemoEdit = (EditText)findViewById(R.id.editText_memo);
    }


    View.OnClickListener listener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                // 1. 파일에 저장된 메모 텍스트 파일 불러오기
                case R.id.load: {
                    String memoData = mTextFileManager.load();
                    mMemoEdit.setText(memoData);

                    Toast.makeText(memo.this, "불러오기 완료", Toast.LENGTH_LONG).show();
                    break;
                }
                // 2. editText에 입력된 메모를 텍스트 파일(memo.text)에 저장하기
                case R.id.save: {
                    String memoData = mMemoEdit.getText().toString();
                    mTextFileManager.save(memoData);
                    mMemoEdit.setText("");

                    Toast.makeText(memo.this, "저장 완료", Toast.LENGTH_LONG).show();
                    break;
                }
                // 3. 저장된 메모 파일 삭제하기
                case R.id.delete: {
                    mTextFileManager.delete();
                    mMemoEdit.setText("");

                    Toast.makeText(memo.this, "삭제 완료", Toast.LENGTH_LONG).show();
                    break;
                }

            }}
    };
}