package com.eventstore.bookdatabase.diaryapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.travelplan.Travel;
import com.eventstore.bookdatabase.diaryapp.travelplan.TravelAdapter;
import com.eventstore.bookdatabase.diaryapp.travelplan.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class TravelCalendarFragment extends Fragment {
    RecyclerView recyclerView;
    TravelAdapter travelAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_travel_calendar, container, false);
        recyclerView=view.findViewById(R.id.title_bar_layout);
        setupRecyclerView();
        return view;
    }

    void setupRecyclerView(){
        Query query= Utility.getCollectionReference().orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Travel> options=new FirestoreRecyclerOptions.Builder<Travel>().setQuery(query,Travel.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        travelAdapter=new TravelAdapter(options,getActivity());
        recyclerView.setAdapter(travelAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        travelAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        travelAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        travelAdapter.notifyDataSetChanged();
    }
}