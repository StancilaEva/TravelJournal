package com.example.traveljournalstancilaeva.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournalstancilaeva.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final ImageView tripImg;
    private final TextView tripName;
    private final TextView tripDestination;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);
        tripImg = itemView.findViewById(R.id.iv_tripItem_picture);
        this.tripName = itemView.findViewById(R.id.tv_tripItem_name);
        this.tripDestination = itemView.findViewById(R.id.tv_tripItem_destination);
    }

    public ImageView getTripImg() {
        return tripImg;
    }

    public TextView getTripName() {
        return tripName;
    }

    public TextView getTripDestination() {
        return tripDestination;
    }
}
