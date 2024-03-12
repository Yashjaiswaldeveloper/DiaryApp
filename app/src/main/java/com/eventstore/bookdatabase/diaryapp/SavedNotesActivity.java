package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.eventstore.bookdatabase.diaryapp.adapter.MyFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.pesonal.adsdk.AppManage;

public class SavedNotesActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MyFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_notes);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager);
        AppManage.getInstance(SavedNotesActivity.this).loadInterstitialAd(this);

        tabLayout.addTab(tabLayout.newTab().setText("Diary Notes"));
        tabLayout.addTab(tabLayout.newTab().setText("Daily Expenses"));
        tabLayout.addTab(tabLayout.newTab().setText("Event Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Travel Planner"));
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager , getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }
    public void onBackPressed() {
        AppManage.getInstance(SavedNotesActivity.this).showInterstitialAd(SavedNotesActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                Intent intent=new Intent(SavedNotesActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        }, "", AppManage.app_mainClickCntSwAd);



    }
}