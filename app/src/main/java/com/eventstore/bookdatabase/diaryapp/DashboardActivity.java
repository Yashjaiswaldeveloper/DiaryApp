package com.eventstore.bookdatabase.diaryapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.eventstore.bookdatabase.diaryapp.calendarDecorators.CalendarSplashActivity;
import com.eventstore.bookdatabase.diaryapp.expense.MainExpenseActivity;
import com.eventstore.bookdatabase.diaryapp.themechanging.ThemeActivity;
import com.eventstore.bookdatabase.diaryapp.travelplan.TravelDiary;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pesonal.adsdk.AppManage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {
    ImageView note_one, note_two, note_three, note_four;
    CircleImageView profileImage;
    StorageReference storageReference;
    DrawerLayout drawer_layout;
    CardView one_menu, two_menu, three_menu, four_menu, five_menu, six_menu, seven_menu, eight_menu;
    TextView diary_app_name, daily_expense_app_name, calendar_app_name, travel_planner_app_name, FullName, Email;
    ImageView diary_note_logo, daily_expense_logo, calendar_logo, travel_planner_logo;
    private ActionBarDrawerToggle toggle;
    ImageView menu_btn;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    private Intent intent = new Intent();
    FirebaseFirestore fStore;
    FirebaseUser user;
    String userID;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        AppManage.getInstance(DashboardActivity.this).loadInterstitialAd(this);
        firebaseAuth = FirebaseAuth.getInstance();
        profileImage = findViewById(R.id.profile_image);
        note_one = findViewById(R.id.note_one);
        menu_btn = findViewById(R.id.menu_btn);
        note_two = findViewById(R.id.note_two);
        note_three = findViewById(R.id.note_three);
        note_four = findViewById(R.id.note_four);
        one_menu = findViewById(R.id.one_menu);
        two_menu = findViewById(R.id.two_menu);
//        FullName=findViewById(R.id.user_name);
//        Email=findViewById(R.id.user_email);
        three_menu = findViewById(R.id.three_menu);
        four_menu = findViewById(R.id.four_menu);
//        five_menu = findViewById(R.id.five_menu);
        six_menu = findViewById(R.id.six_menu);
//        seven_menu = findViewById(R.id.seven_menu);
//        eight_menu = findViewById(R.id.eight_menu);
        diary_app_name = findViewById(R.id.diary_app_name);
        calendar_app_name = findViewById(R.id.calendar_app_name);
        daily_expense_app_name = findViewById(R.id.daily_expense_app_name);
        travel_planner_app_name = findViewById(R.id.travel_planner_app_name);
        diary_note_logo = findViewById(R.id.diary_note_logo);
        daily_expense_logo = findViewById(R.id.daily_expense_logo);
        calendar_logo = findViewById(R.id.calendar_logo);
        travel_planner_logo = findViewById(R.id.travel_planner_logo);
        drawer_layout = findViewById(R.id.drawer_layout);
        userID = firebaseAuth.getCurrentUser().getUid();
        user = (FirebaseUser) firebaseAuth.getCurrentUser();

        storageReference = (StorageReference) FirebaseStorage.getInstance().getReference();
//        DocumentReference documentReference = fStore.collection("users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.d(TAG, "Listen failed.", e);
//                    return;
//                }
//                if (documentSnapshot.exists()) {
//                    FullName.setText(documentSnapshot.getString("fullname"));
//                    Email.setText(documentSnapshot.getString("email"));
//                } else {
//                    Log.d("tag", "onEvent: Document do not exists");
//                }
//            }
//        });
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        one_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        two_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, MainExpenseActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);


            }
        });
        three_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, CalendarSplashActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        four_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, TravelDiary.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
//        five_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
//                    public void callbackCall() {
//                        Intent intent = new Intent(DashboardActivity.this, SavedNotesActivity.class);
//                        startActivity(intent);
//                    }
//                }, "", AppManage.app_mainClickCntSwAd);
//
//            }
//        });
        six_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DashboardActivity.this)
                        .setMessage("Are you sure you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(DashboardActivity.this, LoginActivityOne.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
        navigationView = findViewById(R.id.navigationView);
        storageReference = (StorageReference) FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get().load(uri).into(profileImage));
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
//            Toast.makeText(this, "SetUp Completed", Toast.LENGTH_SHORT).show();
//            initializeLogic();
        }

        note_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        note_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), MainExpenseActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        note_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), CalendarSplashActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        note_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), TravelDiary.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

    }
//    private void initializeLogic() {
//        android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable(); s.setColor(Color.parseColor("#ffffff")); s.setCornerRadius(10); note_one.setBackground(s);
//        note_one.setBackground(s);
//        note_two.setBackground(s);
//        note_three.setBackground(s);
//        note_four.setBackground(s);
//    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                intent.setClass(getApplicationContext(), ThopThankUActivity.class);
                startActivity(intent);
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}