package com.example.traveljournalstancilaeva.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournalstancilaeva.AddTripActivity;
import com.example.traveljournalstancilaeva.R;
import com.example.traveljournalstancilaeva.fragments.HomeFragment;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder>{
    private ArrayList<Trip> tripsArrayList;
    private OnClick onClick;
    public TripAdapter(ArrayList<Trip> tripsArrayList,OnClick onClick) {
        this.tripsArrayList = tripsArrayList;
        this.onClick = onClick;
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
        holder.getRatingBar().setRating((float) currentTrip.getRate());
        holder.getTripPrice().setText(String.valueOf(currentTrip.getPrice()*10)+" Euros");
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onClick!=null){
                    int pos = tripsArrayList.indexOf(currentTrip);
                    if(pos != RecyclerView.NO_POSITION){
                        onClick.onItemLongClick(pos);
                    }
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return tripsArrayList.size();
    }
}
