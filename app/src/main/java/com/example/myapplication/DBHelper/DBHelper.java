package com.example.myapplication.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "db.db";

    public DBHelper(@Nullable Context context) {
        super(context, "db.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //MyDB.execSQL("create Table users(username TEXT primary key ,password TEXT )");
        MyDB.execSQL("create Table PPLProgram(id TEXT primary key ,day TEXT,password TEXT,bodyPart TEXT,equipement TEXT,gifURL TEXT,name TEXT,target TEXT)");
        MyDB.execSQL("INSERT into PPLProgram values ('25', 'push', 'chest', 'barbell', 'http://d205bpvrqc9yn1.cloudfront.net/0025.gif', 'barbell bench press', 'pectorals')");
        Log.i("test", "created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists PPLProgram");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;

    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username =? and password =?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;
    }
}

