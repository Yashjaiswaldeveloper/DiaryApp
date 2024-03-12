package com.eventstore.bookdatabase.diaryapp.event;

import android.text.format.DateUtils;

import androidx.recyclerview.widget.RecyclerView;

import com.eventstore.bookdatabase.diaryapp.databinding.EventRowBinding;


public class RosterRowHolder extends RecyclerView.ViewHolder {
    final private EventRowBinding binding;
    private final RosterListAdapter adapter;

    public RosterRowHolder(EventRowBinding binding, RosterListAdapter adapter) {
        super(binding.getRoot());

        this.binding=binding;
        this.adapter=adapter;
    }

    void bind(EventModel model) {
        binding.setModel(model);
        binding.setDueDate(DateUtils.getRelativeTimeSpanString(model.dueDate().getTimeInMillis(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS));
        binding.setHolder(this);
        binding.executePendingBindings();
    }

    public void onClick(){
        adapter.showModel(binding.getModel());
    }

}