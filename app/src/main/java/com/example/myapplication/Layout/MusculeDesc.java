package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.muscleActivity;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MusculeDesc extends AppCompatActivity {

    int repN=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscule_desc);
        Dialog dialog = new Dialog(MusculeDesc.this);
        //set text for equipement
        TextView equipement =(TextView) findViewById(R.id.exerciceEquipement);
        String equipementI = getIntent().getStringExtra("equipement");
        equipement.setText(equipementI);
        //set text for name
        TextView name =(TextView) findViewById(R.id.exerciceName);
        String nameI = getIntent().getStringExtra("name");
        name.setText(nameI);
        //set text for target
        TextView target =(TextView) findViewById(R.id.exercicetarget);
        String targetI = getIntent().getStringExtra("target");
        target.setText(targetI);
        //set text for bodyPart
        TextView bodyPart =(TextView) findViewById(R.id.exerciceBodyPart);
        String bodyPartI = getIntent().getStringExtra("bodyPart");
        bodyPart.setText(bodyPartI+" workout");
        //set image
        String gifURL = getIntent().getStringExtra("gifURL");
        ImageView gif =(ImageView)findViewById(R.id.gifImage) ;
        Glide.with(getApplicationContext())
                .load(gifURL)
                .into(gif);
        //favorite btn
        ImageButton favorite =(ImageButton)findViewById(R.id.favorite);
        favorite.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite.setSelected(!favorite.isPressed());
                if(favorite.isPressed()){
                    favorite.setImageResource(R.drawable.star1);
                }
                else {
                    favorite.setImageResource(R.drawable.star);
                }

            }
        }));
        //back btn
        ImageButton backBtn =(ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusculeDesc.this.onBackPressed();
            }
        }));
        //timer
        TextView counter =(TextView)findViewById(R.id.counter);
        TextView rep =(TextView)findViewById(R.id.exercicerep);

        long  duration = TimeUnit.MINUTES.toMillis(1);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.counter);

        Button counterStrat =(Button)findViewById(R.id.counterStrat);
        counterStrat.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                counterStrat.setEnabled(false);
                new CountDownTimer(duration, 1000) {

                    @Override
                    public void onTick(long l) {
                        String sDuration =String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l),
                                TimeUnit.MILLISECONDS.toSeconds(l)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                        counter.setText(String.valueOf(sDuration));
                    }

                    @Override
                    public void onFinish() {
                        //counter.setVisibility(View.GONE);
                        counterStrat.setEnabled(true);
                        mp.stop();
                        if(--repN!=0){
                            rep.setText("x"+(--repN)+" rep");
                        }else {
                            dialog.setContentView(R.layout.dialog_congratulations);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(false);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            Button okay_text = dialog.findViewById(R.id.congratDone);
                            okay_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    MusculeDesc.this.onBackPressed();

                                }
                            });
                            dialog.show();
                        }



                    }
                }.start();
                mp.start();
            }
        }));

    }

}