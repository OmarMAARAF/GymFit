package com.example.myapplication.Layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DBHelper.DBHelper;
import com.example.myapplication.DBHelper.InfosDB;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    SharedPreferences sharedPreferences;
    ImageButton logoutBtn;

    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SEXE = "sexee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intention = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intention);
            }
        });
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String sexee = sharedPreferences.getString(KEY_SEXE, null);
         if(sexee.equals("female"))
        {
            ImageView imgView=(ImageView) findViewById(R.id.avatar);
            Drawable drawable  = getResources().getDrawable(R.drawable.avatarwomen);
            imgView.setImageDrawable(drawable);
        }else if(sexee.equals("Male")){
             ImageView imgView=(ImageView) findViewById(R.id.avatar);
             Drawable drawable  = getResources().getDrawable(R.drawable.avatarmen);
             imgView.setImageDrawable(drawable);
         }
        DBHelper helper = new DBHelper(this);
        InfosDB newDB = new InfosDB(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        //fullname
        Cursor cursor = database.rawQuery("SELECT * FROM users where email = ? ",new String[]{email});

        if(cursor != null)
        {
            cursor.moveToNext();
        }

        StringBuilder builder = new StringBuilder();
        do{
            String fullname = cursor.getString(0);
            builder.append(" " + fullname);

        }while(cursor.moveToNext());

        TextView fullname = (TextView) findViewById(R.id.fullname);
        fullname.setText(builder.toString());

        //fullname1
        Cursor cursor1 = database.rawQuery("SELECT * FROM users where email = ? ",new String[]{email});

        if(cursor1 != null)
        {
            cursor1.moveToNext();
        }

        StringBuilder builder1 = new StringBuilder();
        do{
            String fullname1 = cursor1.getString(0);
            builder1.append(" " + fullname);

        }while(cursor1.moveToNext());

        TextView fullname1 = (TextView) findViewById(R.id.profileName);
        fullname1.setText(builder.toString());
        //email
        Cursor cur = database.rawQuery("SELECT * FROM users where email = ? ",new String[]{email});

        if(cur != null)
        {
            cur.moveToNext();
        }

        StringBuilder build = new StringBuilder();
        do{

            String emaill = cur.getString(1);
            build.append(" " + emaill);

        }while(cur.moveToNext());

        TextView emaill = (TextView) findViewById(R.id.email);
        emaill.setText(build.toString());
        //sexe
        SQLiteDatabase dbase = newDB.getReadableDatabase();
        Cursor curss = dbase.rawQuery("SELECT * FROM info where email = ? ",new String[]{email});

        if(curss != null)
        {
            curss.moveToNext();
        }

        do{
            String sexeee = curss.getString(1);
            if(sexeee.equals("female"))
            {
                ImageView imgView=(ImageView) findViewById(R.id.avatar);
                Drawable drawable  = getResources().getDrawable(R.drawable.avatarwomen);
                imgView.setImageDrawable(drawable);
            }else if(sexeee.equals("Male")){
                ImageView imgView=(ImageView) findViewById(R.id.avatar);
                Drawable drawable  = getResources().getDrawable(R.drawable.avatarmen);
                imgView.setImageDrawable(drawable);
            }

        }while(curss.moveToNext());
        Cursor curs = dbase.rawQuery("SELECT * FROM info where email = ? ",new String[]{email});

        if(curs != null)
        {
            curs.moveToNext();
        }

        StringBuilder builde = new StringBuilder();
        do{
            String sexe = curs.getString(1);
            builde.append(" " + sexe);

        }while(curs.moveToNext());

        TextView sexe = (TextView) findViewById(R.id.sexe);
        sexe.setText(builde.toString());
        //Age
        Cursor curs1 = dbase.rawQuery("SELECT * FROM info where email = ? ",new String[]{email});

        if(curs1 != null)
        {
            curs1.moveToNext();
        }

        StringBuilder builde1 = new StringBuilder();
        do{
            String age = curs1.getString(4);
            builde1.append(" " + age + " ans");

        }while(curs1.moveToNext());

        TextView age = (TextView) findViewById(R.id.age);
        age.setText(builde1.toString());
        //height
        Cursor curs2 = dbase.rawQuery("SELECT * FROM info where email = ? ",new String[]{email});

        if(curs2 != null)
        {
            curs2.moveToNext();
        }

        StringBuilder builde2 = new StringBuilder();
        do{
            String height = curs2.getString(2);
            builde2.append(" " + height + "cm");

        }while(curs2.moveToNext());

        TextView height = (TextView) findViewById(R.id.height);
        height.setText(builde2.toString());
        //Weight
        Cursor cursor3 = dbase.rawQuery("SELECT * FROM info where email = ? ",new String[]{email});

        if(cursor3 != null)
        {
            cursor3.moveToNext();
        }

        StringBuilder builder3 = new StringBuilder();
        do{
            String poids = cursor3.getString(3);
            builder3.append(""+ poids +" Kg");

        }while(cursor3.moveToNext());

        TextView poids = (TextView) findViewById(R.id.poids);
        poids.setText(builder3.toString());

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null)
        {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
        }


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
        //=====

    }
}