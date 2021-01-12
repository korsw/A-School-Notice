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
    RecyclerView mRecyclerView = null;
    ShowNoticeBoardAdapter mAdapter = null;
    ArrayList<ShowNoticeBoardItem> mList;

    private Drawable mImageDrawable;
    private String mMainText;
    private String mSubText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run(){
                Crawler crawler = new Crawler();
                try {
                    String title = crawler.covidNotice();
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
        }, 2000);
    }
}