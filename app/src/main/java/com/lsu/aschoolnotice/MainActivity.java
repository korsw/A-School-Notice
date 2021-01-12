package com.lsu.aschoolnotice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Crawler crawler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run(){
                Crawler crawler = new Crawler();
                try {
                    crawler.main();
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