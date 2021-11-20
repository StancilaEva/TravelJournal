package com.example.traveljournalstancilaeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Handler handler = new Handler();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                         startActivity(intent);
                         finish();
                     }
                 },2000);

             };
         }).start();
    }
}