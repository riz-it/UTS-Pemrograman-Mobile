package com.example.uts_pemrograman_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        Loading();
    }

    void Loading(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}