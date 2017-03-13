package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by 재윤 on 2017-03-09.
 */

public class ListDataBase extends SQLiteOpenHelper{
    ArrayList<String> listdata=new ArrayList<>();

    public ListDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql2 = "CREATE TABLE Todo_List3"+"(PLACE TEXT, PLAN TEXT, TITLE TEXT, CONTENT TEXT, time TEXT);";//TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void insert(String _query){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void update(String _query){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void delete(String _query){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public ArrayList<String> search(){
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM Todo_List3",null);
        while(cursor.moveToNext()){
            listdata.add(cursor.getString(2));
        }
        db.close();
        return listdata;
    }
}
