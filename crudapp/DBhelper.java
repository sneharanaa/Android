package com.example.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(@Nullable Context context) {
        super(context, "storedb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student (id integer primary key autoincrement , name text , age number , dob text , course text /*, genderradio text , gendercheck text*/)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists student");
    }

    public boolean insert(String name , int age , String dob , String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name" , name);
        cv.put("age" , age);
        cv.put("dob" , dob);
        cv.put("course" , course);
        //cv.put("genderradio" ,genderradio);
        //cv.put("gendercheck" , gendercheck);
        long res = db.insert("student" , null , cv);
        return res != -1;
    }

    public boolean update(String name , int age , String dob , String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name" , name);
        cv.put("age" , age);
        cv.put("dob" , dob);
        cv.put("course" , course);
        long res = db.update("student" , cv , "name = ?" , new String[]{name});
        return res > 0;
    }

    public boolean delete(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete("student" , "name = ? " , new String[]{name});
        return  res > 0;
    }

    public Cursor view(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from student order by name ASC" , null);
    }

}
