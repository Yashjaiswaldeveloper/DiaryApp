package com.eventstore.bookdatabase.diaryapp.travelplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.eventstore.bookdatabase.diaryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class TravelPannerActivity extends AppCompatActivity {
    public EditText ed1, ed2;
    TextView textview1;
    CardView button;
    public static String edit1, edit2;
    String title, content, docId;
    public boolean isEditMode = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_panner);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        textview1 = (TextView) findViewById(R.id.textview1);
        button = findViewById(R.id.button);
//        title = getIntent().getStringExtra("title");
//        content = getIntent().getStringExtra("content");
//        docId = getIntent().getStringExtra("docId");
//        if (docId != null && !docId.isEmpty()) {
//            isEditMode = true;
//        }
//        ed1.setText(title);
//        ed2.setText(content);
//        if (isEditMode) {
//            textview1.setText("Edit your Travel Plan");
//        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit1 = ed1.getText().toString().trim();
                edit2 = ed2.getText().toString().trim();
                if (edit1==null||edit1.isEmpty()){
                    ed1.setError("Destination is required");
                    return;
                }
                Travel travel=new Travel();
                travel.setTitle(edit1);
                travel.setContent(edit2);
                travel.setTimestamp(Timestamp.now());
//                saveNoteToFirebase(travel);
//                long createdTime = System.currentTimeMillis();
//                Bundle bundle = new Bundle();
//                bundle.putString("edit1", edit1);
//                bundle.putString("edit2", edit2);
//                realm.beginTransaction();
//                Note note = realm.createObject(Note.class);
//                note.setTitle(edit1);
//                note.setDescription(edit2);
//                note.setCreatedTime(createdTime);
//                realm.commitTransaction();
//                Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_SHORT).show();
//                finish();
                Intent intent = new Intent(TravelPannerActivity.this, TravelPlannerTwoActivity.class);
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
//                    Toast.makeText(TravelPannerActivity.this, "Travel Plan added Successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(TravelPannerActivity.this,TravelPlannerTwoActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(TravelPannerActivity.this, "Failed to record your travel plan", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}