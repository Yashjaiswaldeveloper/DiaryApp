package com.eventstore.bookdatabase.diaryapp.calendarDecorators;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.eventstore.bookdatabase.diaryapp.DashboardActivity;
import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.event.EventReminderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class CalendarSplashActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextView FullName;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    String userID;
    FirebaseUser user;
    public final int REQ_CD_FILES = 101;
    private Timer _timer = new Timer();
    private TimerTask timmm;
    private Intent intent = new Intent();
    private Intent files = new Intent(Intent.ACTION_GET_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_splash);
        FullName=findViewById(R.id.username);
        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        user = (FirebaseUser) firebaseAuth.getCurrentUser();
        storageReference = (StorageReference) FirebaseStorage.getInstance().getReference();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Listen failed.", e);
                    return;
                }
                if (documentSnapshot.exists()) {
                    FullName.setText(documentSnapshot.getString("fullname"));
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
        timmm = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        intent.setClass(getApplicationContext(), EventReminderActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        };
        _timer.schedule(timmm, (int)(2000));
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(CalendarSplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}