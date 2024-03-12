package com.eventstore.bookdatabase.diaryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivityOne extends AppCompatActivity {
    EditText email;
    EditText passworrd;
    FirebaseAuth firebaseAuth;
    TextView loginButton;
    ProgressBar progressBar;
    private TextView for_pass,tvplus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_one);

        tvplus=(TextView)findViewById(R.id.tvplus);
        email = (EditText) findViewById(R.id.editTextEmail);
        passworrd =(EditText) findViewById(R.id.editTextPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        for_pass = (TextView) findViewById(R.id.forgot_password);
        loginButton = (TextView) findViewById(R.id.cirLoginButton);
        progressBar = findViewById(R.id.logprogressBar);
        tvplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityOne.this, RegistrationActivity.class));
            }
        });

        loginButton.setOnClickListener(view -> {
            final String Email = email.getText().toString().trim();
            String Password = passworrd.getText().toString().trim();
            if (TextUtils.isEmpty(Email)) {
                email.setError("Email is Required.");
                return;
            }

            if (TextUtils.isEmpty(Password)) {
                passworrd.setError("Password is Required.");
                return;
            }

            if (Password.length() < 6) {
                passworrd.setError("Password Must be more than 6 Characters");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            //authenticate user
            firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivityOne.this, "Logged In Succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), PrivacyPolicyActivity.class));
                } else {
                    Toast.makeText(LoginActivityOne.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        }
        for_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText reset_mail = new EditText(v.getContext());
                final AlertDialog.Builder passResetDialog = new AlertDialog.Builder(v.getContext());
                passResetDialog.setTitle("Reset Password");
                passResetDialog.setMessage("Enter your Email ID to Receive Reset Link");
                passResetDialog.setView(reset_mail);
                passResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send the rest link
                        String mail = reset_mail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivityOne.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(LoginActivityOne.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog box
                    }
                });
                passResetDialog.create().show();
            }
        });
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}