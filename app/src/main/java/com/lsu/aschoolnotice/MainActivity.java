package com.lsu.aschoolnotice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("NewNotice");

        new Thread(){
            @Override
            public void run(){
                try {
                    Crawler.Covidstart();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                try {
                    Crawler.Bachelorstart();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                try {
                    Crawler.Employmentstart();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        startLoading();
    }

    private void startLoading(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), NoticeBoard.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

}