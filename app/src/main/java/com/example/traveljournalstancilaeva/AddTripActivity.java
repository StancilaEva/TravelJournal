package com.example.traveljournalstancilaeva;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class AddTripActivity extends AppCompatActivity {


    SeekBar tripPrice;
    TextInputEditText tripName;
    TextInputEditText tripStartDate;
    TextInputEditText tripEndDate;
    RadioGroup tripTypeGroup;
    TextView tripPriceTv;
    RatingBar ratingBar;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        initComponents();
    }

    private void initComponents() {
        tripPrice = findViewById(R.id.s_addTripActivity_euro);
        tripName = findViewById(R.id.tiet_addTripActivity_tripName);
        tripStartDate = findViewById(R.id.tiet_addTripActivity_startDate);
        tripEndDate = findViewById(R.id.tiet_addTripActivity_endDate);
        ratingBar = findViewById(R.id.rb_add_trip_activity_rating);
        tripPriceTv = findViewById(R.id.tv_addTripActivity_euro);
        tripTypeGroup = findViewById(R.id.rg_addTripActivity_tripType);
        saveButton = findViewById(R.id.b_addTripActivity_save);
        saveButton.setOnClickListener(setOnClickListener());
        tripPrice.setOnSeekBarChangeListener(seekBarChangeListener());

    }

    private View.OnClickListener setOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i*=10;
                tripPriceTv.setText(getString(R.string.price_travel_item,i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }
}