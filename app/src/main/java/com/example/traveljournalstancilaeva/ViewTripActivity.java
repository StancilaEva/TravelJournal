package com.example.traveljournalstancilaeva;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.traveljournalstancilaeva.fragments.HomeFragment;
import com.example.traveljournalstancilaeva.util.DateConverter;
import com.example.traveljournalstancilaeva.util.Trip;

public class ViewTripActivity extends AppCompatActivity {

    private TextView tvTripName;
    private TextView tvTripDestination;
    private TextView tvTripPrice;
    private TextView tvTripStartDate;
    private TextView tvTripEndDate;
    private RatingBar tvTripRating;
    Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        initComponents();
        getInfo();
    }

    private void getInfo() {
        Bundle giftMessage = getIntent().getExtras();
        if(giftMessage!=null){
            trip = (Trip)giftMessage.getParcelable(HomeFragment.SINGLE_TRIP);
            initComponentValues();
        }
    }

    private void initComponentValues() {
        tvTripName.setText(trip.getName());
        tvTripDestination.setText(trip.getDestination());
        tvTripPrice.setText(String.valueOf(trip.getPrice()*10)+" Euros");
        tvTripStartDate.setText(DateConverter.fromDate(trip.getStartDate()));
        tvTripEndDate.setText(DateConverter.fromDate(trip.getEndDate()));
        tvTripRating.setRating((float) trip.getRate());
    }



    private void initComponents() {
        tvTripName = findViewById(R.id.tv_view_trip_activity_trip_name);
        tvTripDestination = findViewById(R.id.tv_view_trip_activity_destination);
        tvTripPrice = findViewById(R.id.tv_view_trip_activity_price);
        tvTripStartDate = findViewById(R.id.tv_view_trip_activity_start_date);
        tvTripEndDate = findViewById(R.id.tv_view_trip_activity_end_date);
        tvTripRating = findViewById(R.id.rb_view_trip_activity_rating);
    }
}