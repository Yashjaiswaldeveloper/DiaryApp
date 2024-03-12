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

public class TravelPlannerThreeActivity extends AppCompatActivity {
    public EditText ed5,ed6;
    CardView button;
    public static String edit5,edit6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_planner_three);
        ed5=(EditText)findViewById(R.id.ed5);
        ed6=(EditText)findViewById(R.id.ed6);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit5 = ed5.getText().toString().trim();
                edit6 = ed6.getText().toString().trim();
//                Bundle bundle = new Bundle();
//                bundle.putString("edit5", edit5);
//                bundle.putString("edit6", edit6);
                Travel travel=new Travel();
                travel.setShop(edit5);
                travel.setContacts(edit6);
                travel.setTimestamp(Timestamp.now());
//                saveNoteToFirebase(travel);

                Intent intent = new Intent(TravelPlannerThreeActivity.this, TravelPlannerFourActivity.class);
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
//                    Toast.makeText(TravelPlannerThreeActivity.this, "Travel Plan added Successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(TravelPlannerThreeActivity.this,TravelPlannerFourActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(TravelPlannerThreeActivity.this, "Failed to record your travel plan", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}