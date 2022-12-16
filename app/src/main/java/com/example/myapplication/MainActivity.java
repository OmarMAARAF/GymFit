package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.DBHelper.DBHelper;
import com.example.myapplication.Layout.CreateAccount;
import com.example.myapplication.Layout.LoginActivity;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn2, btn1;
    ChipNavigationBar chipNavigationBar;
    DBHelper myDB;
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        imageSlider = findViewById(R.id.imageSlider);
        myDB = new DBHelper(this);
        ArrayList<SlideModel> slideModels = new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.img11, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img12, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img13, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img14, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intention = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intention);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intention = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intention);
            }
        });
    }
}