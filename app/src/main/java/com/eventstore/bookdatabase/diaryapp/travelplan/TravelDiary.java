package com.eventstore.bookdatabase.diaryapp.travelplan;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eventstore.bookdatabase.diaryapp.DashboardActivity;
import com.eventstore.bookdatabase.diaryapp.DiaryNotesActivity;
import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.event.EventReminderActivity;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.pesonal.adsdk.AppManage;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class TravelDiary extends AppCompatActivity {
    private ImageView back;
    ImageView add_note_btn;
    RecyclerView recyclerView;
    TravelAdapter travelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_diary);
        AppManage.getInstance(TravelDiary.this).loadInterstitialAd(this);
        AppManage.getInstance(TravelDiary.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        back = (ImageView) findViewById(R.id.back);
        add_note_btn = (ImageView) findViewById(R.id.add_note_btn);
        recyclerView=findViewById(R.id.title_bar_layout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(TravelDiary.this).showInterstitialAd(TravelDiary.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        startActivity(new Intent(TravelDiary.this, DashboardActivity.class));
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        add_note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(TravelDiary.this).showInterstitialAd(TravelDiary.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        startActivity(new Intent(TravelDiary.this, TravelingDataOverallActivity.class));
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        setupRecyclerView();

    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(TravelDiary.this).showInterstitialAd(TravelDiary.this, new AppManage.MyCallback() {
            public void callbackCall() {
                startActivity(new Intent(TravelDiary.this, DashboardActivity.class));
                finish();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    void setupRecyclerView(){
        Query query=Utility.getCollectionReference().orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Travel> options=new FirestoreRecyclerOptions.Builder<Travel>().setQuery(query,Travel.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        travelAdapter=new TravelAdapter(options,this);
        recyclerView.setAdapter(travelAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        travelAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        travelAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        travelAdapter.notifyDataSetChanged();
    }
}