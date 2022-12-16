package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DBHelper.DBHelper;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button btnLogin;
    DBHelper myDB;
    SharedPreferences sharedPreferences;
    TextView RegisterNow;

    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        myDB = new DBHelper(this);
        RegisterNow =(TextView)findViewById(R.id.RegisterFromLogin);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);


        //Redirect to Registartion
        RegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAccount.class);
                startActivity(intent);
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter the Credentials . ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result = myDB.checkPassword(user,pass);
                    if(result == true)
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL,email.getText().toString());
                        editor.apply();
                        //  Toast.makeText(LoginActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Crediantails", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}