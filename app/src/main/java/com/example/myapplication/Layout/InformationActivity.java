package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.myapplication.DBHelper.InfosDB;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityInformationBinding;

public class InformationActivity extends AppCompatActivity {
    ActivityInformationBinding binding;
    int intWeight = 50;
    int intAge = 20;
    int currentProgress;
    String mintProgress ="170";
    String typeOfUser = "0";
    String weight2 = "50";
    String age2 ="20";
    InfosDB myDB;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SEXE = "sexee";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_AGE = "age";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDB = new InfosDB(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);


        if (getSupportActionBar() != null)
            getSupportActionBar().hide();



        binding.male.setOnClickListener(view -> {
            binding.male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.card_back_btn));
            binding.female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.card_back_not_focus));
            typeOfUser="Male";
        });
        binding.female.setOnClickListener(view -> {
            binding.female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.card_back_btn));
            binding.male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.card_back_not_focus));
            typeOfUser="female";
        });

        binding.seekbar.setMax(300);
        binding.seekbar.setProgress(170);
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress = i;
                mintProgress = String.valueOf(currentProgress);
                binding.currentHeight.setText(mintProgress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.incrementAge.setOnClickListener(view ->{
            intAge = intAge+1;
            age2 = String.valueOf(intAge);
            binding.currentAge.setText((age2));
        });

        binding.decrementAge.setOnClickListener(view -> {
            intAge = intAge-1;
            age2 = String.valueOf(intAge);
            binding.currentAge.setText((age2));
        });

        binding.incrementWeight.setOnClickListener(view ->{
            intWeight = intWeight+1;
            weight2 = String.valueOf(intWeight);
            binding.currentWeight.setText((weight2));
        });

        binding.decrementWeight.setOnClickListener(view ->{
            intWeight = intWeight-1;
            weight2 = String.valueOf(intWeight);
            binding.currentWeight.setText((weight2));
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeOfUser.equals("Male"))
                {

                    Boolean regResult =   myDB.insertinfo(email,typeOfUser,mintProgress,weight2,age2);
                    if(regResult == true )
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_SEXE, typeOfUser);
                        editor.apply();
                        Toast.makeText(InformationActivity.this, "Insertion Info", Toast.LENGTH_SHORT).show();
                        Intent intention = new Intent(InformationActivity.this, Home.class);
                        startActivity(intention);
                    }
                }
                if(typeOfUser.equals("female"))
                {
                    Boolean regResult =   myDB.insertinfo(email,typeOfUser,mintProgress,weight2,age2);
                    if(regResult == true )
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_SEXE, typeOfUser);
                        editor.putString(KEY_HEIGHT, mintProgress);
                        editor.putString(KEY_WEIGHT, weight2);
                        editor.putString(KEY_AGE, age2);
                        editor.apply();
                        Toast.makeText(InformationActivity.this, "Insertion Info", Toast.LENGTH_SHORT).show();
                        Intent intention = new Intent(InformationActivity.this, Home.class);
                        startActivity(intention);
                    }
                }


            }
        });
    }
}