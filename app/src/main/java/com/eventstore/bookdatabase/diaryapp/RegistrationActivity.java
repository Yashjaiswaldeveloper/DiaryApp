package com.eventstore.bookdatabase.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth firebaseAuth;
    TextView bregister;
    EditText name, email, phone, pass,dob;
    FirebaseFirestore fStore;
    ProgressBar progressBar;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        bregister = findViewById(R.id.cirRegisterButton);
        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.editTextMobile);
        dob = findViewById(R.id.editTextdob);
        fStore = FirebaseFirestore.getInstance();
        pass = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.regprogressBar);

        bregister.setOnClickListener(view -> {
            final String Email = email.getText().toString().trim();
            String Password = pass.getText().toString().trim();
            final String FullName = name.getText().toString();
            final String Phone = phone.getText().toString();
            final String DOB = dob.getText().toString();
            if (TextUtils.isEmpty(FullName)) {
                name.setError("Name is Required.");
                return;
            }
            if (TextUtils.isEmpty(Email)) {
                email.setError("Email is Required.");
                return;
            }
            if (TextUtils.isEmpty(Phone)) {
                phone.setError("Phone Number is Required.");
                return;
            }
            if (TextUtils.isEmpty(DOB)) {
                dob.setError("Date of Birth is Required.");
                return;
            }
            if (Phone.length() > 10) {
                phone.setError("Phone Number should not exceed more than 10");
                return;
            }
            if (TextUtils.isEmpty(Password)) {
                pass.setError("Password is Required.");
                return;
            }
            if (Password.length() < 6) {
                pass.setError("Password Must be more than 6 Characters");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    firebaseUser.sendEmailVerification().addOnSuccessListener(aVoid -> Toast.makeText(RegistrationActivity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure:Email not sent" + e.getMessage());
                        }
                    });
                    Toast.makeText(RegistrationActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("fullname", FullName);
                    user.put("email", Email);
                    user.put("phone", Phone);
                    user.put("dob", DOB);
                    documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: User Profile is created for " + userID));
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                } else {
                    Toast.makeText(RegistrationActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            });
        });
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
