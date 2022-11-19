package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.muscleActivity;

public class Home extends AppCompatActivity {

    Button chestBtn;
    Button backBtn;
    Button absBtn;
    Button legsBtn;
    Button bicepsBtn;
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


    }
}