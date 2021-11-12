package com.example.traveljournalstancilaeva.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournalstancilaeva.R;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder>{
    private ArrayList<Trip> tripsArrayList;

    public TripAdapter(ArrayList<Trip> tripsArrayList) {
        this.tripsArrayList = tripsArrayList;
    }


    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item,parent,false);
       return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = tripsArrayList.get(position);
        holder.getTripName().setText(currentTrip.getName());
        holder.getTripDestination().setText(currentTrip.getDestination());
    }

    @Override
    public int getItemCount() {
        return tripsArrayList.size();
    }
}
