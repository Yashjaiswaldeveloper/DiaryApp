package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pesonal.adsdk.AppManage;

public class PrivacyPolicyActivity extends AppCompatActivity {
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        AppManage.getInstance(PrivacyPolicyActivity.this).loadInterstitialAd(this);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(PrivacyPolicyActivity.this).showInterstitialAd(PrivacyPolicyActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(PrivacyPolicyActivity.this,ProfileActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}