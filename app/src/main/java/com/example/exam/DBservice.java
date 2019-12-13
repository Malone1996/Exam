package com.example.exam;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBservice {
    private SQLiteDatabase db;
    private String Unit;

    public DBservice(){
        db=SQLiteDatabase.openDatabase("/data/data/com.example.exam/databases/question.db",null,SQLiteDatabase.OPEN_READWRITE);


    }

    //修改数据库
    public void updataStar(int i){
//        ContentValues values = new ContentValues();
//        values.put("ID",i+"");
//        db.update("question", values, "ID=?", new int[] {i});
        String sql = "update question set star = '已收藏' where id="+i;
        db.execSQL(sql);
    }

    public void updataCancelStar(int i){
        String sql = "update question set star = '未收藏' where id="+i;
        db.execSQL(sql);
    }

    public List<Question> getQuestions(String unit){
        this.Unit=unit;
        List<Question> list=new ArrayList<Question>();
        Cursor cursor=db.rawQuery("select * from question where unit="+this.Unit,null);
//        Cursor cursor=db.rawQuery("select * from question",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            int count=cursor.getCount();

            //使用循环将cursor中的每一条记录都生成一个Question对象，并将其添加到List中
            for (int i=0;i<count;i++){
                cursor.moveToPosition(i);
                Question question=new Question();
                question.question=cursor.getString(cursor.getColumnIndex("question"));
                question.answerA=cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB=cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC=cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD=cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer=cursor.getInt(cursor.getColumnIndex("answer"));
                question.unit=cursor.getString(cursor.getColumnIndex("unit"));
                question.ID=cursor.getInt(cursor.getColumnIndex("ID"));
                question.explaination=cursor.getString(cursor.getColumnIndex("explaination"));
                question.star=cursor.getString(cursor.getColumnIndex("star"));

                question.selectedAnswer=-1;//表示未选择任何对象
                list.add(question);


            }


        }
        return list;
    }

    public List<Question> getFavoriteQuestions(String unit){
        this.Unit=unit;
        String star_str="'已收藏'";
        List<Question> list=new ArrayList<Question>();
        Cursor cursor=db.rawQuery("select * from question where unit="+this.Unit+" and star="+star_str,null);
//        Cursor cursor=db.rawQuery("select * from question",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            int count=cursor.getCount();

            //使用循环将cursor中的每一条记录都生成一个Question对象，并将其添加到List中
            for (int i=0;i<count;i++){
                cursor.moveToPosition(i);
                Question question=new Question();
                question.question=cursor.getString(cursor.getColumnIndex("question"));
                question.answerA=cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB=cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC=cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD=cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer=cursor.getInt(cursor.getColumnIndex("answer"));
                question.unit=cursor.getString(cursor.getColumnIndex("unit"));
                question.ID=cursor.getInt(cursor.getColumnIndex("ID"));
                question.explaination=cursor.getString(cursor.getColumnIndex("explaination"));
                question.star=cursor.getString(cursor.getColumnIndex("star"));

                question.selectedAnswer=-1;//表示未选择任何对象
                list.add(question);


            }


        }
        return list;
    }

}
