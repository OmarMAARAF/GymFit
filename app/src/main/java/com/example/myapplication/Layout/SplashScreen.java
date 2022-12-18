package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class SplashScreen extends AppCompatActivity {
    public static final String MyPREFERENCES = "mypref";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String userSession =(sharedpreferences.getString("user",""));
        Thread thread =new Thread(){
            @Override
            public  void run(){
                try{
                    sleep(3000);

                    Log.i("userSession",userSession);
                    if(userSession.equals("session")){
                        Intent i =new Intent(getApplicationContext(),Home.class);
                        startActivity(i);
                    }
                    else{
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }

                    finish();
                }catch(Exception e){

                }
            }
        };
        thread.start();
    }
}