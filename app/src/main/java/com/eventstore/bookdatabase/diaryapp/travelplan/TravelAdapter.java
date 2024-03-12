package com.eventstore.bookdatabase.diaryapp.travelplan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eventstore.bookdatabase.diaryapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import io.realm.Realm;

public class TravelAdapter extends FirestoreRecyclerAdapter<Travel,TravelAdapter.TravelViewHolder> {
    Context context;


    public TravelAdapter(@NonNull FirestoreRecyclerOptions<Travel> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull TravelViewHolder holder, int position, @NonNull Travel travel) {
        holder.titleTextView.setText(travel.title);
        holder.one.setText("Destination");
        holder.contentTextView.setText(travel.content);
        holder.two.setText("When");
        holder.note_see_text_view.setText(travel.see);
        holder.three.setText("Place To See");
        holder.note_eat_text_view.setText(travel.eat);
        holder.four.setText("Place To Eat");
        holder.note_shop_text_view.setText(travel.shop);
        holder.five.setText("Place To Shop");
        holder.note_contacts_text_view.setText(travel.contacts);
        holder.six.setText("Emergency Contacts");
        holder.note_expense_text_view.setText(travel.expense);
        holder.seven.setText("Total Expense");
        holder.note_address_text_view.setText(travel.address);
        holder.eight.setText("Address");
        holder.timestampTextView.setText(Utility.timestamToString(travel.timestamp));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TravelingDataOverallActivity.class);
                intent.putExtra("title",travel.title);
                intent.putExtra("content",travel.content);
                intent.putExtra("see",travel.see);
                intent.putExtra("eat",travel.eat);
                intent.putExtra("shop",travel.shop);
                intent.putExtra("contacts",travel.contacts);
                intent.putExtra("expense",travel.expense);
                intent.putExtra("address",travel.address);
                String docId=getSnapshots().getSnapshot(position).getId();
                intent.putExtra("docId",docId);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.noteview,parent,false);
        return new TravelViewHolder(view);
    }

    class TravelViewHolder extends RecyclerView.ViewHolder {
        TextView one,two,three,four,five,six,seven,eight;
        TextView titleTextView, contentTextView, timestampTextView,note_see_text_view,note_eat_text_view,note_shop_text_view,note_contacts_text_view,note_expense_text_view,note_address_text_view;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            one = itemView.findViewById(R.id.note_title);
            contentTextView = itemView.findViewById(R.id.note_content_text_view);
            two = itemView.findViewById(R.id.note_content);
            note_see_text_view = itemView.findViewById(R.id.note_see_text_view);
            three = itemView.findViewById(R.id.note_see);
            note_eat_text_view = itemView.findViewById(R.id.note_eat_text_view);
            four = itemView.findViewById(R.id.note_eat);
            note_shop_text_view = itemView.findViewById(R.id.note_shop_text_view);
            five = itemView.findViewById(R.id.note_shop);
            note_contacts_text_view = itemView.findViewById(R.id.note_contacts_text_view);
            six = itemView.findViewById(R.id.note_contacts);
            note_expense_text_view = itemView.findViewById(R.id.note_expense_text_view);
            seven = itemView.findViewById(R.id.note_expense);
            note_address_text_view = itemView.findViewById(R.id.note_address_text_view);
            eight = itemView.findViewById(R.id.note_address);
            timestampTextView = itemView.findViewById(R.id.note_timestamp_text_view);
        }
    }
}
