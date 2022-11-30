package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Layout.CalendarViewActivity;
import com.example.myapplication.Layout.SearchActivity;
import com.example.myapplication.R;
import com.example.myapplication.muscleActivity;

public class Home extends AppCompatActivity {

    Button chestBtn;
    Button backBtn;
    Button absBtn;
    Button legsBtn;
    Button bicepsBtn;
    ImageButton calendarBtn;
    EditText search_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        chestBtn =(Button)findViewById(R.id.chestBtn);
        chestBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), muscleActivity.class);
                i.putExtra("muscle","chest");
                startActivity(i);
            }
        }));
        backBtn =(Button)findViewById(R.id.backBtn);
        backBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),muscleActivity.class);
                i.putExtra("muscle","back");
                startActivity(i);
            }
        }));
        //calendar activity
        calendarBtn =(ImageButton)findViewById(R.id.homeCalendarBtn);
        calendarBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), CalendarViewActivity.class);
                startActivity(i);
            }
        }));
        absBtn =(Button)findViewById(R.id.absBtn);
        absBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),muscleActivity.class);
                i.putExtra("muscle","waist");
                startActivity(i);
            }
        }));
        legsBtn =(Button)findViewById(R.id.legsBtn);
        legsBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),muscleActivity.class);
                i.putExtra("muscle","upper legs");
                startActivity(i);
            }
        }));
        bicepsBtn =(Button)findViewById(R.id.bicepsBtn);
        bicepsBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),muscleActivity.class);
                i.putExtra("muscle","upper arms");
                startActivity(i);
            }
        }));
        //today Layout
        RelativeLayout todayLayout =(RelativeLayout)findViewById(R.id.todayinfo);
        todayLayout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), CalendarViewActivity.class);
                startActivity(i);
            }
        }));
        //Search
        Intent searchIntent =new Intent(getApplicationContext(), SearchActivity.class);
        search_bar =(EditText)findViewById(R.id.search_bar);
        search_bar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    searchIntent.putExtra("search",search_bar.getText().toString());
                    startActivity(searchIntent);
                    return true;
                }
                return false;
            }

        });


    }
}