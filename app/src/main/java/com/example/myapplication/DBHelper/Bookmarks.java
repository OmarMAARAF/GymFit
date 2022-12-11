package com.example.myapplication.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Beans.Muscle;

import java.util.ArrayList;
import java.util.List;

public class Bookmarks extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fitnessData";
    private static final String TABLE_BOOKMAKS= "bookmarks";

    // Bookmark Table Columns names
    private static final String KEY_ID = "id_bookmark";
    private static final String USER_ID = "id_user";
    private static final String bodyPart = "bodyPart";
    private static final String equipment = "equipment";
    private static final String gifUrl = "gifUrl";
    private static final String id = "id";
    private static final String name = "name";
    private static final String target = "target";

    public Bookmarks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKMARKS_TABLE = "CREATE TABLE " + TABLE_BOOKMAKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + USER_ID + " INTEGER,"
                + bodyPart + " TEXT,"+ equipment + " TEXT,"+ gifUrl + " TEXT,"
                + id + " TEXT,"+ name + " TEXT,"+ target + " TEXT"+")";
        db.execSQL(CREATE_BOOKMARKS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMAKS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new bookmark
    public void addBookmark(Muscle muscle,int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, userId ); // user Id
        values.put(bodyPart , muscle.getBodyPart());
        values.put(equipment  , muscle.getEquipment ());
        values.put(gifUrl  , muscle.getGifUrl ());
        values.put(id  , muscle.getId());
        values.put(name  , muscle.getName ());
        values.put(target  , muscle.getTarget ());


        // Inserting Row
        db.insert(TABLE_BOOKMAKS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Bookmark
    public Boolean getBookmark(String idMuscle,int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKMAKS, new String[] { KEY_ID,USER_ID,bodyPart,equipment,gifUrl,id,name,target},
                USER_ID + " =? and "+id+" =?",
                new String[] { idMuscle,String.valueOf(userId) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Muscle m = new Muscle(cursor.getString(2),
                cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),
                cursor.getString(7));
        // return country
        return true;
    }

    public Boolean checkBookmark(int userID,String idMuscle ){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("select * from "+TABLE_BOOKMAKS +" where +"+id+" =?",new String[] {idMuscle});

        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;

    }
    // Getting All Bookmarks
    public List getAllBookmarks() {
        List bookmarkList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKMAKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Muscle m = new Muscle(cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),
                        cursor.getString(7));
                // Adding country to list
                bookmarkList.add(m);
            } while (cursor.moveToNext());
        }
        // return country list
        return bookmarkList;
    }


    // Deleting single country
    public void deleteBookmark(String idBookmark,String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKMAKS, id + " = ? and "+USER_ID+" =?",
                new String[] {idBookmark,userId  });
     //   db.close();
    }

    // Getting countries Count
    public int getBookmarkssCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BOOKMAKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}

