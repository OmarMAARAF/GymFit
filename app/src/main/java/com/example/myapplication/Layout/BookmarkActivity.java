package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.DBHelper.DBHelper;
import com.example.myapplication.R;

public class BookmarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        //backbtn
        ImageButton bookmarkBackBtn=(ImageButton)findViewById(R.id.bookmarkBackBtn);
        bookmarkBackBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookmarkActivity.this.onBackPressed();
            }
        }));
        //Connection to db
        DBHelper DB =new DBHelper(this);
        /*Boolean checkBookMark=DB.checkBookmark(1,"1");
        Log.i("checkB",checkBookMark.toString());*/
    }
}