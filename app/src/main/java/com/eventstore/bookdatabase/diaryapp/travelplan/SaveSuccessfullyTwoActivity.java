package com.eventstore.bookdatabase.diaryapp.travelplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eventstore.bookdatabase.diaryapp.DiaryNotesActivity;
import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.SAvedSuccesfullThreeActivity;
import com.eventstore.bookdatabase.diaryapp.SavedSuccessfullyOne;
import com.eventstore.bookdatabase.diaryapp.expense.MainExpenseActivity;
import com.pesonal.adsdk.AppManage;

public class SaveSuccessfullyTwoActivity extends AppCompatActivity {
    TextView ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_successfully_two);
        AppManage.getInstance(SaveSuccessfullyTwoActivity.this).loadInterstitialAd(this);
        ok=findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(SaveSuccessfullyTwoActivity.this).showInterstitialAd(SaveSuccessfullyTwoActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(SaveSuccessfullyTwoActivity.this, MainExpenseActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }
}