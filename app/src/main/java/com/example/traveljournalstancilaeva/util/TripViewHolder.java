package com.example.traveljournalstancilaeva.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournalstancilaeva.AddTripActivity;
import com.example.traveljournalstancilaeva.R;

import org.w3c.dom.Text;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final ImageView tripImg;
    private final TextView tripName;
    private final TextView tripDestination;
    private final TextView tripPrice;
    private final RatingBar ratingBar;

    public TextView getTripPrice() {
        return tripPrice;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);
        tripImg = itemView.findViewById(R.id.iv_tripItem_picture);
        this.tripName = itemView.findViewById(R.id.tv_tripItem_name);
        this.tripDestination = itemView.findViewById(R.id.tv_tripItem_destination);
        this.tripPrice = itemView.findViewById(R.id.tv_trip_item_price);
        this.ratingBar = itemView.findViewById(R.id.rb_trip_item_rating);
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
