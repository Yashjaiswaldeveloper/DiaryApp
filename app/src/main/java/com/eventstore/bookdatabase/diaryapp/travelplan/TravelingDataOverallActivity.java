package com.eventstore.bookdatabase.diaryapp.travelplan;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eventstore.bookdatabase.diaryapp.DashboardActivity;
import com.eventstore.bookdatabase.diaryapp.HomeActivity;
import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.SAvedSuccesfullThreeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.pesonal.adsdk.AppManage;

public class TravelingDataOverallActivity extends AppCompatActivity {
    TextView savebtn, textview1;
    ImageView delete,back;
    EditText text1,text2,text3,text4,text5,text6,text7,text8;
    String title, content, see, eat, shop, contacts, expense, address, docId;
    public boolean isEditMode = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveling_data_overall);
        AppManage.getInstance(TravelingDataOverallActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(TravelingDataOverallActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        text1 = findViewById(R.id.ed1);
        text2 = findViewById(R.id.ed2);
        text3 = findViewById(R.id.ed3);
        text4 = findViewById(R.id.ed4);
        text5 = findViewById(R.id.ed5);
        text6 = findViewById(R.id.ed6);
        text7 = findViewById(R.id.ed7);
        text8 = findViewById(R.id.ed8);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(TravelingDataOverallActivity.this).showInterstitialAd(TravelingDataOverallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(TravelingDataOverallActivity.this,TravelDiary.class);
                        startActivity(intent);
                        finish();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        TextView savebtn = findViewById(R.id.savebtn);
        TextView textview1 = findViewById(R.id.textview1);
        delete = findViewById(R.id.delete);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        see = getIntent().getStringExtra("see");
        eat = getIntent().getStringExtra("eat");
        shop = getIntent().getStringExtra("shop");
        contacts = getIntent().getStringExtra("contacts");
        expense = getIntent().getStringExtra("expense");
        address = getIntent().getStringExtra("address");
        docId = getIntent().getStringExtra("docId");
        if (docId != null && !docId.isEmpty()) {
            isEditMode = true;
        }
        text1.setText(title);
        text2.setText(content);
        text3.setText(see);
        text4.setText(eat);
        text5.setText(shop);
        text6.setText(contacts);
        text7.setText(expense);
        text8.setText(address);
        if (isEditMode) {
            textview1.setText("Edit your Travel Plan");
            delete.setVisibility(View.VISIBLE);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNoteFromFirebase();
            }
        });

//        text1.setText(TravelPannerActivity.edit1);
//        text2.setText(TravelPannerActivity.edit2);
//        text3.setText(TravelPlannerTwoActivity.edit3);
//        text4.setText(TravelPlannerTwoActivity.edit4);
//        text5.setText(TravelPlannerThreeActivity.edit5);
//        text6.setText(TravelPlannerThreeActivity.edit6);
//        text7.setText(TravelPlannerFourActivity.edit7);
//        text8.setText(TravelPlannerFourActivity.edit8);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(TravelingDataOverallActivity.this).showInterstitialAd(TravelingDataOverallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        saveNote();
                    }
                }, "", AppManage.app_mainClickCntSwAd);


            }
        });

    }

    private void deleteNoteFromFirebase() {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReference().document(docId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(TravelingDataOverallActivity.this, "Travel Plan Deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TravelingDataOverallActivity.this, TravelDiary.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(TravelingDataOverallActivity.this, "Failed while deleting plan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveNote() {
        String trtitle=text1.getText().toString();
        String trcontent=text2.getText().toString();
        String trsee=text3.getText().toString();
        String treat=text4.getText().toString();
        String trshop=text5.getText().toString();
        String trcontacts=text6.getText().toString();
        String trexpense=text7.getText().toString();
        String traddress=text8.getText().toString();
        if (trtitle==null||trtitle.isEmpty()){
            text1.setError("Destination is required");
            return;
        }

        Travel travel = new Travel();
        travel.setTitle(trtitle);
        travel.setContent(trcontent);
        travel.setSee(trsee);
        travel.setEat(treat);
        travel.setShop(trshop);
        travel.setContacts(trcontacts);
        travel.setExpense(trexpense);
        travel.setAddress(traddress);
        travel.setTimestamp(Timestamp.now());
        saveNoteToFirebase(travel);
    }

    public void saveNoteToFirebase(Travel travel) {
        DocumentReference documentReference;
        if (isEditMode) {
            documentReference = Utility.getCollectionReference().document(docId);
        } else {
            documentReference = Utility.getCollectionReference().document();
        }
        documentReference.set(travel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(TravelingDataOverallActivity.this, "Travel added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TravelingDataOverallActivity.this, SAvedSuccesfullThreeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(TravelingDataOverallActivity.this, "Failed to record your travel plan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        AppManage.getInstance(TravelingDataOverallActivity.this).showInterstitialAd(TravelingDataOverallActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                Intent intent=new Intent(TravelingDataOverallActivity.this,TravelDiary.class);
                startActivity(intent);
                finish();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}