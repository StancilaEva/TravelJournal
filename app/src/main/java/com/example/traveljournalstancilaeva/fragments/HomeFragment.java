package com.example.traveljournalstancilaeva.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.traveljournalstancilaeva.AddTripActivity;
import com.example.traveljournalstancilaeva.R;
import com.example.traveljournalstancilaeva.util.Trip;
import com.example.traveljournalstancilaeva.util.TripAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TRIP_LIST = "TRIP_LIST";
    ArrayList<Trip> tripList;
    FloatingActionButton fabAddTrip;

    RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(ArrayList<Trip> tripList) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(TRIP_LIST,tripList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tripList = getArguments().getParcelableArrayList(TRIP_LIST);
        }
    }

    private void setUpAdapter() {
        TripAdapter tripAdapter = new TripAdapter(tripList);
        recyclerView.setAdapter(tripAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if(view.getContext()!=null) {
            recyclerView = view.findViewById(R.id.rv_homeFragment_trips);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            setUpAdapter();
            fabAddTrip = view.findViewById(R.id.fab_home_fragment);
            fabAddTrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AddTripActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }


}