package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pesonal.adsdk.AppManage;

public class SetupCompleteActivity extends AppCompatActivity {
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_complete);
        AppManage.getInstance(SetupCompleteActivity.this).loadInterstitialAd(this);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(SetupCompleteActivity.this).showInterstitialAd(SetupCompleteActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(SetupCompleteActivity.this,CheckingActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(SetupCompleteActivity.this).showInterstitialAd(SetupCompleteActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                SetupCompleteActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}