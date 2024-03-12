package com.eventstore.bookdatabase.diaryapp.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.eventstore.bookdatabase.diaryapp.DashboardActivity;
import com.eventstore.bookdatabase.diaryapp.HomeActivity;
import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.calendarDecorators.CalendarSplashActivity;
import com.pesonal.adsdk.AppManage;

public class EventReminderActivity extends AppCompatActivity implements HomeFragment.Contract, DisplayFragment.Contract, EditFragment.Contract {

    private static final String BACK_STACK_SHOW = "showModel";
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(EventReminderActivity.this).showInterstitialAd(EventReminderActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(EventReminderActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//        setSupportActionBar(toolbar);

        //Set default state of app (HomeFragment)
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finishEdit(false);
        return super.onSupportNavigateUp();
    }

    @Override
    public void showModel(EventModel model) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, DisplayFragment.newInstance(model))
                .addToBackStack(BACK_STACK_SHOW)
                .commit();

    }

    @Override
    public void editModel(EventModel model) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, EditFragment.newInstance(model))
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void finishEdit(boolean deleted) {
        hideSoftInput();
        if (deleted) {
            getSupportFragmentManager().popBackStack(BACK_STACK_SHOW,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

    private void hideSoftInput() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void addModel() {
        editModel(null);
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(EventReminderActivity.this).showInterstitialAd(EventReminderActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                Intent intent=new Intent(EventReminderActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}
