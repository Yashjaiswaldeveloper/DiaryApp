package com.eventstore.bookdatabase.diaryapp.themechanging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.eventstore.bookdatabase.diaryapp.R;

import java.util.ArrayList;

public class ThemeColorsAdapter extends RecyclerView.Adapter<ThemeColorsAdapter.viewHolder> {

    private ArrayList<ColorModel> colorModels;
    private Context context;
    private OnItemClick clickListener;

    public ThemeColorsAdapter(ArrayList<ColorModel> colorModels, Context context, OnItemClick clickListener) {
        this.colorModels = colorModels;
        this.context = context;
        this.clickListener = clickListener;
    }

    interface OnItemClick{
        void onItemClick(int pos);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_color, parent, false);
        return new viewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ColorModel colorModel = colorModels.get(position);
        holder.cvBackground.setCardBackgroundColor(colorModel.getColor());
        if(colorModel.isSelected()){
            holder.ivCheck.setVisibility(View.VISIBLE);
        }else {
            holder.ivCheck.setVisibility(View.GONE);
        }
        holder.cvBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorModels.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        CardView cvBackground;
        ImageView ivCheck;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cvBackground = itemView.findViewById(R.id.cvBackground);
            ivCheck = itemView.findViewById(R.id.ivCheck);
        }
    }
}
