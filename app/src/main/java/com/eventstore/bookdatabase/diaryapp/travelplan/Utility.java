package com.eventstore.bookdatabase.diaryapp.travelplan;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class Utility {
    public static CollectionReference getCollectionReference(){
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("travel").document(currentUser.getUid()).collection("mytravel");
    }
    static String timestamToString(Timestamp timestamp){
       return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());

    }
}
