package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eventstore.bookdatabase.diaryapp.expense.MainExpenseActivity;
import com.eventstore.bookdatabase.diaryapp.travelplan.SaveSuccessfullyTwoActivity;
import com.pesonal.adsdk.AppManage;

public class SavedSuccessfullyOne extends AppCompatActivity {
    TextView ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_successfully_one);
        AppManage.getInstance(SavedSuccessfullyOne.this).loadInterstitialAd(this);
        ok=findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(SavedSuccessfullyOne.this).showInterstitialAd(SavedSuccessfullyOne.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(SavedSuccessfullyOne.this,DiaryNotesActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }
}