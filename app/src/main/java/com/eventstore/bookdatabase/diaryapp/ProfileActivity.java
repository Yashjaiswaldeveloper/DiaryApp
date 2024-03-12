package com.eventstore.bookdatabase.diaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pesonal.adsdk.AppManage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static final String TAG1 = "tag";
    TextView emailNotVerified, Profile, FullName, Email, Phone, FullNameNew,DOB;
    ImageView changeProfileImage;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    String userID;
    ImageView back,Change_Profile;
    TextView done,privacy_policy;
    FirebaseUser user;
    CircleImageView profileImage;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppManage.getInstance(ProfileActivity.this).loadInterstitialAd(this);
        firebaseAuth = FirebaseAuth.getInstance();
        changeProfileImage =findViewById(R.id.Change_Profile);
        back =findViewById(R.id.back);
        done =findViewById(R.id.done);
        profileImage = findViewById(R.id.ProfileImage);
        privacy_policy = findViewById(R.id.privacy_policy);
        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://techiemediaadvertising.blogspot.com/2022/06/techiemedia-inc.html");
            }
        });
        DOB = findViewById(R.id.textDOB);
        fStore = FirebaseFirestore.getInstance();
        FullName = (TextView) findViewById(R.id.textName);
        Email = (TextView) findViewById(R.id.textEmail);
        Phone = (TextView) findViewById(R.id.textPhone);
        userID = (String) firebaseAuth.getCurrentUser().getUid();
        user = (FirebaseUser) firebaseAuth.getCurrentUser();
        storageReference = (StorageReference) FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get().load(uri).into(profileImage));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(ProfileActivity.this).showInterstitialAd(ProfileActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        ProfileActivity.super.onBackPressed();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Listen failed.", e);
                    return;
                }
                if (documentSnapshot.exists()) {
                    Phone.setText(documentSnapshot.getString("phone"));
                    FullName.setText(documentSnapshot.getString("fullname"));
                    Email.setText(documentSnapshot.getString("email"));
                    DOB.setText(documentSnapshot.getString("dob"));
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
        changeProfileImage.setOnClickListener(v -> {
            AppManage.getInstance(ProfileActivity.this).showInterstitialAd(ProfileActivity.this, new AppManage.MyCallback() {
                public void callbackCall() {
                    //open gallery
                    Intent i = new Intent(v.getContext(), LoginActivity.class);
                    i.putExtra("FullName", FullName.getText().toString());
                    i.putExtra("Email", Email.getText().toString());
                    i.putExtra("Phone", Phone.getText().toString());
                    i.putExtra("DOB", DOB.getText().toString());
                    startActivity(i);
                }
            }, "", AppManage.app_mainClickCntSwAd);

        });

        FirebaseUser user = firebaseAuth.getCurrentUser();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(ProfileActivity.this).showInterstitialAd(ProfileActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        intent.setClass(getApplicationContext(), SetupCompleteActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(ProfileActivity.this).showInterstitialAd(ProfileActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                ProfileActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}