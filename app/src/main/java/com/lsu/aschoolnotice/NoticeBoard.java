package com.lsu.aschoolnotice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    RecyclerView mRecyclerView = null; //리사이클러뷰
    ShowNoticeBoardAdapter mAdapter = null; //리사이클러뷰 어뎁터
    ArrayList<ShowNoticeBoardItem> mList; //리사이클러뷰 내부 아이템 리스트


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
                Intent intent = new Intent(getApplicationContext() ,PopupActivity.class);
                intent.putExtra("NoticeTitle", (String) Crawler.getCovidlist().get(getPositionNum(position)));
                intent.putExtra("NoticeText", (String) Crawler.getCovidTextlist().get(position));
                startActivityForResult(intent, 1);
            }
        }) ;

        TextView Ctextview = (TextView) findViewById(R.id.nav_Covidlist);
        Ctextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListtoAddItem(Crawler.getCovidlist());
                Toast.makeText(getApplicationContext(), "코로나 공지사항입니다.",  Toast.LENGTH_LONG).show();
            }
        });

        TextView Btextview = (TextView) findViewById(R.id.nav_Bachelorlist);
        Btextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListtoAddItem(Crawler.getBachelorlist());
                Toast.makeText(getApplicationContext(), "학사 공지사항입니다.",  Toast.LENGTH_LONG).show();
            }
        });

        TextView Etextview = (TextView) findViewById(R.id.nav_Employmentlist);
        Btextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListtoAddItem(Crawler.getEmploymentlist());
                Toast.makeText(getApplicationContext(), "취업 공지사항입니다.",  Toast.LENGTH_LONG).show();
            }
        });

        //ListtoAddItem(Crawler.getCovidlist());
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
        for (int i = 0; i < list.size(); i += 5){
            String mMainText = (String) list.get(i+1);
            String mSubText = (String) list.get(i+3);
            addItem(mMainText , mSubText);
        }
    }

    private int getPositionNum(int position){
        return position*5+1;
    }
}