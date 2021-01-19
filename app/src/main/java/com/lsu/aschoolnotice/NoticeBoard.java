package com.lsu.aschoolnotice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class NoticeBoard extends AppCompatActivity {
    RecyclerView mRecyclerView = null; //리사이클러뷰
    ShowNoticeBoardAdapter mAdapter = null; //리사이클러뷰 어뎁터
    ArrayList<ShowNoticeBoardItem> mList; //리사이클러뷰 내부 아이템 리스트
    private String WhatNotice = "Covidlist";


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

        mAdapter.setOnItemClickListener(new ShowNoticeBoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO : 아이템 클릭 이벤트를 MainActivity에서 처리.
                String NoticeTitle = WhatNotice;
                Intent intent = new Intent(getApplicationContext() ,PopupActivity.class);

                intent.putExtra("NoticeTitle", inputNoticeTitle(NoticeTitle, position));
                intent.putExtra("NoticeText", inputNoticeText(NoticeTitle, position));
                startActivityForResult(intent, 1);
            }
        }) ;

        TextView Ctextview = (TextView) findViewById(R.id.nav_Covidlist);
        Ctextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhatNotice = "Covidlist";
                ListtoAddItem(Crawler.getCovidlist());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "코로나 공지사항입니다.",  Toast.LENGTH_LONG).show();
            }
        });

        TextView Btextview = (TextView) findViewById(R.id.nav_Bachelorlist);
        Btextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhatNotice = "Bachelorlist";
                ListtoAddItem(Crawler.getBachelorlist());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "학사 공지사항입니다.",  Toast.LENGTH_SHORT).show();
            }
        });

        TextView Etextview = (TextView) findViewById(R.id.nav_Employmentlist);
        Etextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhatNotice = "Employmentlist";
                ListtoAddItem(Crawler.getEmploymentlist());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "취업 공지사항입니다.",  Toast.LENGTH_SHORT).show();
            }
        });

        ListtoAddItem(Crawler.getCovidlist());
        //ListtoAddItem(Crawler.getBachelorlist());
        //ListtoAddItem(Crawler.getEmploymentlist());

        mAdapter.notifyDataSetChanged();
    }

    private void addItem(String mainText, String subText) {
        ShowNoticeBoardItem item = new ShowNoticeBoardItem();

        item.setMainTitle(mainText);
        item.setSubTitle(subText);

        mList.add(item);
    }


    private void ListtoAddItem(List list){
        mList.clear();
        for (int i = 0; i < list.size(); i += 5){
            String mMainText = (String) list.get(i+1);
            String mSubText = (String) list.get(i+3);
            addItem(mMainText , mSubText);
        }
    }


    private int getPositionNum(int position){
        return position*5+1;
    }

    private String inputNoticeTitle(String notice, int position){
        switch (notice){
            case "Covidlist":
                return (String) Crawler.getCovidlist().get(getPositionNum(position));
            case "Bachelorlist":
                return (String) Crawler.getBachelorlist().get(getPositionNum(position));
            case "Employmentlist":
                return (String) Crawler.getEmploymentlist().get(getPositionNum(position));
            default:
                return (String) Crawler.getCovidlist().get(getPositionNum(position));
        }
    }

    private String inputNoticeText(String notice, int position){
        switch (notice){
            case "Covidlist":
                return (String) Crawler.getCovidTextlist().get(position);
            case "Bachelorlist":
                return (String) Crawler.getBachelorTextlist().get(position);
            case "Employmentlist":
                return (String) Crawler.getEmploymentTextlist().get(position);
            default:
                return (String) Crawler.getCovidTextlist().get(position);
        }
    }
}