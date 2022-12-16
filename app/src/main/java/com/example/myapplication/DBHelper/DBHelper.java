package com.example.myapplication.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context)
    {
        //super(context, database_name , factory , version )
        super(context,"Loggin.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
         myDB.execSQL("create Table users(fullname Text , email text , password text , repassword text)");
         //myDB.execSQL("create Table info(sexe Text , height text , weight text , age text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
          myDB.execSQL("drop Table if exists users");
         // myDB.execSQL("drop Table if exists info");
    }
    public Boolean insertData(String fullname , String email , String password ,String repassword  )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("repassword",repassword);


        long result = myDB.insert("users", null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Boolean insertinfo(String sexe , String height , String weight ,String age )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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

    public Boolean checkemail(String email)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?",new String[] {email});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkPassword(String email,String password)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ? and password= ?",new String[] {email,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*public List<InformationModelClass> getInfosList(){
        String sql = "select * from users ";
        SQLiteDatabase myDB = this.getReadableDatabase();
        List<InformationModelClass> Information = new ArrayList<>();
        Cursor cursor = myDB.rawQuery(sql ,  null);
        if(cursor.moveToFirst())
        {
            do{
                String fullname = cursor.getString(0);
                String email = cursor.getString(1);
                Information.add(new InformationModelClass(fullname , email));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Information;

    }*/


}
