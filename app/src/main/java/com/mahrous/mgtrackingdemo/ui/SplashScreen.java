package com.mahrous.mgtrackingdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.Utility.Param;
import com.mahrous.mgtrackingdemo.ui.login.Login;
import com.mahrous.mgtrackingdemo.ui.mainActivity.MainActivity;

public class SplashScreen extends AppCompatActivity {   private final int SPLASH_DISPLAY_LENGTH = 1000;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        SharedPreferences pref = getSharedPreferences(Param.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        id = pref.getInt(Param.id, 0);


        //linearLayout.setBackgroundResource(R.color.colorPrimary2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (id == 0) {
                    Intent mainIntent = new Intent(SplashScreen.this, Login.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();
                } else {
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);

                    mainIntent.putExtra(Param.id, id);

                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();

                }
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}