package com.lsu.aschoolnotice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawer extends AppCompatActivity {

    ArrayList<ShowNoticeBoardItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        /*
        TextView Ctextview = (TextView) findViewById(R.id.nav_Covidlist);
        Ctextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "1",  Toast.LENGTH_LONG).show();
            }
        });

        TextView Btextview = (TextView) findViewById(R.id.nav_Bachelorlist);
        Btextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "1",  Toast.LENGTH_LONG).show();
            }
        });

        TextView Etextview = (TextView) findViewById(R.id.nav_Employmentlist);
        Btextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "1",  Toast.LENGTH_LONG).show();
            }
        });
         */
    }

    /*public void nav_Covidlist (View v){
        Toast.makeText(getApplicationContext(), "1",  Toast.LENGTH_LONG).show();
    }

    public void nav_Bachelorlist (View v){
        Toast.makeText(getApplicationContext(), "2",  Toast.LENGTH_LONG).show();
    }

    public void nav_Employmentlist (View v){
        Toast.makeText(getApplicationContext(), "3",  Toast.LENGTH_LONG).show();
    }*/

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