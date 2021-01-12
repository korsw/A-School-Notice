package com.lsu.aschoolnotice;

import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoticeBoard extends AppCompatActivity {
    RecyclerView mRecyclerView = null;
    ShowNoticeBoardAdapter mAdapter = null;
    ArrayList<ShowNoticeBoardItem> mList;

    private Drawable mImageDrawable;
    private String mMainText;
    private String mSubText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        mRecyclerView = findViewById(R.id.recycler_view);
        mList = new ArrayList<>();

        mAdapter = new ShowNoticeBoardAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        ListtoAddItem(MainActivity.crawler.Covidlist);
        ListtoAddItem(MainActivity.crawler.Bachelorlist);





        mAdapter.notifyDataSetChanged();
    }

    private void addItem(String mainText, String subText) {
        ShowNoticeBoardItem item = new ShowNoticeBoardItem();

        item.setMainTitle(mainText);
        item.setSubTitle(subText);

        mList.add(item);
    }

    private void ListtoAddItem(List list){
        for (int i = 0; i < list.size(); i += 5){
            String mMainText = (String) list.get(i+1);
            String mSubText = (String) list.get(i+3);
            addItem(mMainText , mSubText);
        }
    }
}