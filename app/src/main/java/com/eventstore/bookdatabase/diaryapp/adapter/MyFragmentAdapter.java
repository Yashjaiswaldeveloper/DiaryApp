package com.eventstore.bookdatabase.diaryapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.eventstore.bookdatabase.diaryapp.expense.DashboardFragment;
import com.eventstore.bookdatabase.diaryapp.fragment.DiaryNotesFragment;
import com.eventstore.bookdatabase.diaryapp.fragment.EventCalendarFragment;
import com.eventstore.bookdatabase.diaryapp.fragment.TravelCalendarFragment;

public class MyFragmentAdapter extends FragmentStateAdapter {


    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DiaryNotesFragment();
        } else if (position == 1) {
            return new DashboardFragment();
        } else if (position == 2) {
            return new EventCalendarFragment();
        } else {
            return new TravelCalendarFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
