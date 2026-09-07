package com.example.sharedpreference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper  extends SQLiteOpenHelper {

    public DBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "storedb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id Integer , name Text , age Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldd, int newd) {
        db.execSQL("drop table if exists student");
    }

    public void insertdata(Integer id , String name , Integer age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id" , id);
        cv.put("name" , name);
        cv.put("age" , age);
        long result = db.insert("student" , null , cv);
    }

    public void updatedata(Integer id , String name , Integer age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("age" , age);
        db.update("student", cv ,"name=?", new String[]{name});
    }

    public int deletedata(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("student" , "name=?" , new String[]{name});
    }

    public void viewdata(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from student" , null);
    }
}
