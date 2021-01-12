package com.lsu.aschoolnotice;

import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;

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




        mImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icon, null);
        mMainText = "내용";
        mSubText = "2021.01.01";

        addItem(mImageDrawable, mMainText,  mSubText);
        addItem(mImageDrawable, mMainText + " - 2",  mSubText);
        addItem(mImageDrawable, mMainText + " - 3",  mSubText);
        addItem(mImageDrawable, mMainText + " - 4",  mSubText);
        addItem(mImageDrawable, mMainText + " - 5",  mSubText);

        mAdapter.notifyDataSetChanged();
    }

    private void addItem(Drawable icon, String mainText, String subText) {
        ShowNoticeBoardItem item = new ShowNoticeBoardItem();

        item.setIcon(icon);
        item.setMainTitle(mainText);
        item.setSubTitle(subText);

        mList.add(item);
    }
}