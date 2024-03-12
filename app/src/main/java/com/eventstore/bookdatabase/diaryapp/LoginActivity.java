package com.eventstore.bookdatabase.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pesonal.adsdk.AppManage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {
    EditText lprofileName, lprofileEmail, lprofilePhone, lprofileDOB;
    ImageView lprofileImageView,post_now;
    CircleImageView profile_image;
    FirebaseAuth firebaseAuth;
    TextView lsaveBtn;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent data = getIntent();

        String FullName = data.getStringExtra("FullName");
        String Email = data.getStringExtra("Email");
        String Phone = data.getStringExtra("Phone");
        String DOB = data.getStringExtra("DOB");
        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();
        lprofileName = (EditText) findViewById(R.id.profileName);
        lprofileEmail = (EditText) findViewById(R.id.profileEmail);
        lprofilePhone = (EditText) findViewById(R.id.profilePhone);
        lprofileDOB = (EditText) findViewById(R.id.profileDob);
        lsaveBtn = (TextView) findViewById(R.id.SaveProfileImfo);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        lprofileImageView = (ImageView) findViewById(R.id.profileImageView);
        post_now = (ImageView) findViewById(R.id.post_now);
        post_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Daily Diary:Journal with Lock");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });
        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get().load(uri).into(profile_image));
        lprofileImageView.setOnClickListener(v -> {
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGalleryIntent, 1000);
        });
        lsaveBtn.setOnClickListener(v -> {
            if (lprofileName.getText().toString().isEmpty() || lprofileEmail.getText().toString().isEmpty() || lprofilePhone.getText().toString().isEmpty()|| lprofileDOB.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "One or many fields are Empty", Toast.LENGTH_SHORT).show();
                return;
            }
//            final String Email = lprofileEmail.getText().toString();
            user.updateEmail(Email).addOnSuccessListener(aVoid -> {
                DocumentReference docRef = fStore.collection("users").document(user.getUid());
                Map<String, Object> edited = new HashMap<>();
                edited.put("email", lprofileEmail.getText().toString());
                edited.put("fullname", lprofileName.getText().toString());
                edited.put("phone", lprofilePhone.getText().toString());
                edited.put("dob", lprofileDOB.getText().toString());
                docRef.update(edited).addOnSuccessListener(aVoid1 -> {
                    Toast.makeText(LoginActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    finish();
                });
//                Toast.makeText(LoginActivity.this, "Email is changed", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        });
        lprofileEmail.setText(Email);
        lprofileName.setText(FullName);
        lprofilePhone.setText(Phone);
        lprofileDOB.setText(DOB);
        Log.d(TAG, "onCreate: " + FullName + " " + Email + " " + Phone + " " + DOB);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profile_image))).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(LoginActivity.this).showInterstitialAd(LoginActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                LoginActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);

    }
}