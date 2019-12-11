package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
//    private Button mBtn;
    private Button mBtnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String DB_PATH="/data/data/com.example.exam/databases/";
        String DB_NAME= "question.db";

        //判断数据库是否存在，不存在则创建数据库
        if ((new File(DB_PATH+DB_NAME).exists())==false){
            File dir=new File(DB_PATH);
            if (!dir.exists()){
                dir.mkdir();
            }

            try {
                InputStream is=getBaseContext().getAssets().open(DB_NAME);
                OutputStream os=new FileOutputStream(DB_PATH+DB_NAME);
                byte[] buffer =new byte[1024];
                int length;

                while ((length=is.read(buffer))>0){
                    os.write(buffer,0,length);
                }

                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        mBtn=findViewById(R.id.button);
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,TestActivity.class);
//                startActivity(intent);
//            }
//
//        });


        mBtnList=findViewById(R.id.btn_list);
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });


    }
}
