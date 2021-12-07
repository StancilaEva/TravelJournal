package com.example.traveljournalstancilaeva.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.traveljournalstancilaeva.AddTripActivity;
import com.example.traveljournalstancilaeva.R;
import com.example.traveljournalstancilaeva.ViewTripActivity;
import com.example.traveljournalstancilaeva.room.TripViewModel;
import com.example.traveljournalstancilaeva.util.DateConverter;
import com.example.traveljournalstancilaeva.util.OnClick;
import com.example.traveljournalstancilaeva.util.Trip;
import com.example.traveljournalstancilaeva.util.TripAdapter;
import com.example.traveljournalstancilaeva.util.TripType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnClick {

    public static final String TRIP_LIST = "TRIP_LIST";
    public static final String SEND_TRIPS = "SEND TRIPS";
    public static final String POSITION = "POSITION";
    public static final String EDIT = "EDIT";
    public static final String SAVE = "SAVE";
    public static final String BUTTON_MESSAGE = "BUTTON_MESSAGE";
    public static final String SINGLE_TRIP = "SINGLE_TRIP";
    public static final String BOOKMARK = "BOOKMARK";
    public static final String TRIP = "TRIP";
    ArrayList<Trip> tripList;
    FloatingActionButton fabAddTrip;
    RecyclerView recyclerView;
    int position;
    TripViewModel tripViewModel;



    private ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== Activity.RESULT_OK){
                Intent intent = result.getData();
//                ArrayList<Trip> trips = intent.getParcelableArrayListExtra(AddTripActivity.RESULT_TRIPS);
 //               Trip trip = intent.getParcelableExtra("TRIP");
//                tripList.clear();
//                tripList.addAll(trips);
//                if(intent.getStringExtra("BUTTON_MESSAGE")!=null) {
//                    if (intent.getStringExtra("BUTTON_MESSAGE").equals("SAVE"))
//                        tripViewModel.insert(trip);
//                    else
//                        tripViewModel.update(trip);
//                }

                refreshList();
                TripAdapter tripAdapter = (TripAdapter) recyclerView.getAdapter();
                tripAdapter.notifyDataSetChanged();

            }

        }
    });
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
        TripAdapter tripAdapter = new TripAdapter(tripList,this);
        recyclerView.setAdapter(tripAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if(view.getContext()!=null) {
            recyclerView = view.findViewById(R.id.rv_homeFragment_trips);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
            setUpAdapter();
            initFAB(view);
            refreshList();


        }
        return view;
    }

    private void refreshList() {
        tripViewModel.getAllTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(final List<Trip> trips) {

                TripAdapter tripAdapter = (TripAdapter) recyclerView.getAdapter();
                tripAdapter.setTrips((ArrayList<Trip>) trips);
                tripList= (ArrayList<Trip>) trips;
            }
        });
    }


    private void initFAB(View view) {
        fabAddTrip = view.findViewById(R.id.fab_home_fragment);
        fabAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddTripActivity.class);
                Trip trip = new Trip("","",50,3.0, TripType.SEASIDE,
                        DateConverter.fromString("1/1/2000"),DateConverter.fromString("1/1/2000"));
               // tripList.add(trip);
           //     intent.putParcelableArrayListExtra(SEND_TRIPS,tripList);
           //     intent.putExtra(POSITION,tripList.indexOf(trip));
                intent.putExtra(TRIP,trip);
                intent.putExtra(BUTTON_MESSAGE,SAVE);
                startForResult.launch(intent);
            }
        });
    }


    @Override
    public void onItemLongClick(int position) {
        this.position = position;
        Intent intent = new Intent(this.getContext(), AddTripActivity.class);
//        intent.putParcelableArrayListExtra(HomeFragment.SEND_TRIPS,tripList);
//        intent.putExtra(HomeFragment.POSITION,position);
        intent.putExtra(TRIP,tripList.get(position));
        intent.putExtra(HomeFragment.BUTTON_MESSAGE,HomeFragment.EDIT);
        startForResult.launch(intent);
    }

    @Override
    public void onItemClick(int position,int resource) {
        this.position = position;
        Intent intent = new Intent(this.getContext(), ViewTripActivity.class);
        Trip trip = tripList.get(position);
        intent.putExtra(SINGLE_TRIP,trip);
        intent.putExtra(BOOKMARK,resource);
        startActivity(intent);
    }
}