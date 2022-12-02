package com.example.myapplication.Layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.fragments.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        //bottom Nav bar

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profilActivity);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.bookmarkActivity:
                        Intent i= new Intent(getApplicationContext(),BookmarkActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profilActivity:
                        return true;
                    case  R.id.homeActivity:
                        Intent i1= new Intent(getApplicationContext(),Home.class);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendarActivity:
                        Intent i2= new Intent(getApplicationContext(), CalendarViewActivity.class);
                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i2);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}