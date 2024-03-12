package com.eventstore.bookdatabase.diaryapp.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.eventstore.bookdatabase.diaryapp.R;
import com.eventstore.bookdatabase.diaryapp.event.DisplayFragment;
import com.eventstore.bookdatabase.diaryapp.event.EditFragment;
import com.eventstore.bookdatabase.diaryapp.event.EventModel;
import com.eventstore.bookdatabase.diaryapp.event.HomeFragment;


public class EventCalendarFragment extends Fragment {

    private static final String BACK_STACK_SHOW = "showModel";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event_calendar, container, false);
        //Set default state of app (HomeFragment)
//        if (view.findViewById(R.id.fragment_container) != null) {
//            if (savedInstanceState != null) {
//            }
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, new NewEventFragment())
//                    .commit();
//        }
        return view;
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        finishEdit(false);
//        return super.onSupportNavigateUp();
//    }
//
//    @Override
//    public void showModel(EventModel model) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, DisplayFragment.newInstance(model))
//                .addToBackStack(BACK_STACK_SHOW)
//                .commit();
//
//    }
//
//    @Override
//    public void editModel(EventModel model) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, EditFragment.newInstance(model))
//                .addToBackStack(null)
//                .commit();
//
//    }
//
//    @Override
//    public void finishEdit(boolean deleted) {
//        hideSoftInput();
//        if (deleted) {
//            getSupportFragmentManager().popBackStack(BACK_STACK_SHOW,
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        } else {
//            getSupportFragmentManager().popBackStack();
//        }
//
//    }
//
//    private void hideSoftInput() {
//        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
//            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
//                    .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }
//    }
//
//    @Override
//    public void addModel() {
//        editModel(null);
//    }

}