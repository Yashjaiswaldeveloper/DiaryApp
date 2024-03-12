package com.eventstore.bookdatabase.diaryapp.travelplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eventstore.bookdatabase.diaryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class TravelPlannerFourActivity extends AppCompatActivity {
    public EditText ed7,ed8;
    CardView button;
    public static String edit7,edit8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_planner_four);
        ed7=(EditText)findViewById(R.id.ed7);
        ed8=(EditText)findViewById(R.id.ed8);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit7 = ed7.getText().toString().trim();
                edit8 = ed8.getText().toString().trim();
                Travel travel=new Travel();
                travel.setTitle(TravelPannerActivity.edit1);
                travel.setTitle(TravelPannerActivity.edit2);
                travel.setTitle(TravelPlannerTwoActivity.edit3);
                travel.setTitle(TravelPlannerTwoActivity.edit4);
                travel.setTitle(TravelPlannerThreeActivity.edit5);
                travel.setTitle(TravelPlannerThreeActivity.edit6);
                travel.setExpense(edit7);
                travel.setAddress(edit8);
                travel.setTimestamp(Timestamp.now());
                saveNoteToFirebase(travel);
//                Bundle bundle = new Bundle();
//                bundle.putString("edit7", edit7);
//                bundle.putString("edit8", edit8);
//                Intent intent = new Intent(TravelPlannerFourActivity.this, TravelDiary.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
    }
    public void saveNoteToFirebase(Travel travel){
        DocumentReference documentReference;
        documentReference=Utility.getCollectionReference().document();

        documentReference.set(travel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(TravelPlannerFourActivity.this, "Travel Plan added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(TravelPlannerFourActivity.this,TravelDiary.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(TravelPlannerFourActivity.this, "Failed to record your travel plan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}