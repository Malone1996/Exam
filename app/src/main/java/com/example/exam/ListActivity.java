package com.example.exam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    private ListView mLv;
    private SQLiteDatabase db;//声明数据库对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mLv=findViewById(R.id.lv);

        //1、获取数据库查询的数据源
//        String path=db.getPath();
        String path= Environment.getDataDirectory()+"/data/com.example.exam/databases/question.db";
        /*
        openDatabase(@NonNull String path, @Nullable CursorFactory factory,
            @DatabaseOpenFlags int flags)

         */
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        final Cursor cursor=db.rawQuery("select * from "+Question.TABLE_NAME,null);




        //2、将数据源数据加载到适配器中

        final SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.layout_list_item,cursor,
                new String[]{"name"},new int[]{R.id.tv},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);


        //3、将适配器的数据加载到控件
        mLv.setAdapter(adapter);


        //列表元素跳转
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=null;
//                if (position<100){
//                    intent=new Intent(MainActivity.this,ExamActivity.class);
//
//                }
//                startActivity(intent);


                position+=1;
                Cursor cursor =db.query(Question.TABLE_NAME,null,"_id="+position,null,null,null,null);
                while (cursor.moveToNext()){
                    int index1=cursor.getColumnIndex("_id");
                    String unitid=cursor.getString(index1);

                    Bundle bundle=new Bundle();
                    bundle.putString("FromTag",unitid);
                    Intent intent = new Intent(ListActivity.this, TestActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                cursor.close();
            }
        });
    }
}
