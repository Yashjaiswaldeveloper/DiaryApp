package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class FirstLoadingPageActivity extends AppCompatActivity {
    ImageView logo;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_loading_page);
        logo=findViewById(R.id.logo);
        firebaseAuth = FirebaseAuth.getInstance();
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstLoadingPageActivity.this, SecondLoadingPageActivity.class);
                startActivity(intent);
            }
        });
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        }
    }
}