package com.eventstore.bookdatabase.diaryapp.travelplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
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

public class TravelPlannerTwoActivity extends AppCompatActivity {
    public EditText ed3,ed4;
    CardView button;
    public static String edit3,edit4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_planner_two);
        ed3=(EditText)findViewById(R.id.ed3);
        ed4=(EditText)findViewById(R.id.ed4);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit3 = ed3.getText().toString().trim();
                edit4 = ed4.getText().toString().trim();
                Travel travel=new Travel();
                travel.setSee(edit3);
                travel.setEat(edit4);
                travel.setTimestamp(Timestamp.now());
//                saveNoteToFirebase(travel);
//                Bundle bundle = new Bundle();
//                bundle.putString("edit3", edit3);
////                bundle.putString("edit4", edit4);
                Intent intent = new Intent(TravelPlannerTwoActivity.this, TravelPlannerThreeActivity.class);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
//    public void saveNoteToFirebase(Travel travel){
//        DocumentReference documentReference;
//        documentReference=Utility.getCollectionReference().document();
//
//        documentReference.set(travel).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(TravelPlannerTwoActivity.this, "Travel Plan added Successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(TravelPlannerTwoActivity.this,TravelPlannerThreeActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(TravelPlannerTwoActivity.this, "Failed to record your travel plan", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}