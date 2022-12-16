package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DBHelper.DBHelper;
import com.example.myapplication.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class CreateAccount extends AppCompatActivity {

    EditText fullname,email,password,repassword ;
    Button btnRegistration,fbbtn,GoogleBtn;
    DBHelper myDB;

    CallbackManager callbackManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        GoogleBtn = findViewById(R.id.GoogleBtn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        GoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        fullname = (EditText)findViewById(R.id.fullname);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //startActivity(new Intent(SecondActivity.this,FbActivity.class));
                        //finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        fbbtn = (findViewById(R.id.fbbtn));
        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login to fb
                LoginManager.getInstance().logInWithReadPermissions(CreateAccount.this, Arrays.asList("public_profile"));
            }
        });


        btnRegistration = (Button) findViewById(R.id.btnRegistration);

        myDB = new DBHelper(this);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _fullname = fullname.getText().toString();
                String _email = email.getText().toString();
                String _password = password.getText().toString();
                String _repassword = repassword.getText().toString();


                if(_fullname.equals("") || _email.equals("") || _password.equals("") || _repassword.equals("") )
                {
                    Toast.makeText(CreateAccount.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(_password.equals(_repassword))
                    {
                        Boolean usercheckResult = myDB.checkemail(_email);
                        if(usercheckResult == false)
                        {
                            Boolean regResult =   myDB.insertData(_fullname,_email,_password,_repassword);
                            if(regResult == true )
                            {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(KEY_EMAIL,email.getText().toString());
                                editor.apply();
                                Toast.makeText(CreateAccount.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(CreateAccount.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(CreateAccount.this, "User already Exists . \n Please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(CreateAccount.this, "Password not Matching", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    void signIn()
    {
        Intent signIntent = gsc.getSignInIntent();
        startActivityForResult(signIntent, 1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }
    void navigateToSecondActivity()
    {
        Intent intent = new Intent(CreateAccount.this,ProfileActivity.class);
        startActivity(intent);

    }
}