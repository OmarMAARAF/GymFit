package com.example.myapplication.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class InfosDB extends SQLiteOpenHelper  {


    public InfosDB(Context context)
    {
        //super(context, database_name , factory , version )
        super(context,"Login.db",null,1);
    }
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table info(email text ,sexe text , height text , weight text , age text)");



    }
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {

        myDB.execSQL("drop Table if exists info");
    }


    public Boolean insertinfo(String email ,String sexe , String height , String weight ,String age )
    {
        
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("sexe",sexe);
        contentValues.put("height",height);
        contentValues.put("weight",weight);
        contentValues.put("age",age);


        long result = myDB.insert("info", null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
