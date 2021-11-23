package com.example.traveljournalstancilaeva;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.traveljournalstancilaeva.fragments.HomeFragment;
import com.example.traveljournalstancilaeva.util.DateConverter;
import com.example.traveljournalstancilaeva.util.Trip;
import com.example.traveljournalstancilaeva.util.TripType;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddTripActivity extends AppCompatActivity {


    public static final String RESULT_TRIPS = "RESULT_TRIPS";
    SeekBar tripPrice;
    TextInputEditText tripName;
    TextInputEditText tripDestination;
 //   TextInputEditText tripStartDate;
   // TextInputEditText tripEndDate;
    RadioGroup tripTypeGroup;
    RadioButton seaside;
    RadioButton mountains;
    RadioButton citybreak;
    TextView tripPriceTv;
    RatingBar ratingBar;
    Button saveButton;
    DatePickerDialog dateStartPickerDialog;
    DatePickerDialog dateEndPickerDialog;
    Button startDateButton;
    Button endDateButton;
    ArrayList<Trip> trips;
    Trip tripYouHaveToEdit;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        initComponents();
        getInfo();
    }

    private void getInfo() {
        Bundle giftMessage = getIntent().getExtras();
        if(giftMessage!=null){
            int position = giftMessage.getInt(HomeFragment.POSITION);
            trips = giftMessage.getParcelableArrayList(HomeFragment.SEND_TRIPS);
            tripYouHaveToEdit = trips.get(position);
            saveButton.setText(giftMessage.getString(HomeFragment.BUTTON_MESSAGE));
            initComponentValues();
        }
    }

    private void initComponentValues() {
        tripName.setText(tripYouHaveToEdit.getName());
        tripDestination.setText(tripYouHaveToEdit.getDestination());
        ratingBar.setRating((float) tripYouHaveToEdit.getRate());
        endDateButton.setText(DateConverter.fromDate(tripYouHaveToEdit.getEndDate()));
        startDateButton.setText(DateConverter.fromDate(tripYouHaveToEdit.getStartDate()));
        switch(tripYouHaveToEdit.getTripType()){
            case CITYBREAK:
                citybreak.setChecked(true);
                break;
            case SEASIDE:
                seaside.setChecked(true);
                break;
            case MOUNTAINS:
                mountains.setChecked(true);
                break;

        }
        tripPrice.setProgress(tripYouHaveToEdit.getPrice());
    }

    @Override
    public void onBackPressed() {
        if(saveButton.getText().toString().equals(HomeFragment.EDIT))finish();
        else{
            trips.remove(tripYouHaveToEdit);
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra(RESULT_TRIPS, trips);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void initComponents() {
        tripPrice = findViewById(R.id.s_addTripActivity_euro);
        tripName = findViewById(R.id.tiet_addTripActivity_tripName);
       // tripStartDate = findViewById(R.id.tiet_addTripActivity_startDate);
        //tripEndDate = findViewById(R.id.tiet_addTripActivity_endDate);
        ratingBar = findViewById(R.id.rb_add_trip_activity_rating);
        tripPriceTv = findViewById(R.id.tv_addTripActivity_euro);
        tripTypeGroup = findViewById(R.id.rg_addTripActivity_tripType);
        saveButton = findViewById(R.id.b_addTripActivity_save);
        startDateButton = findViewById(R.id.b_add_activity_start_date);
        endDateButton =findViewById(R.id.b_add_activity_end_date);
        tripDestination = findViewById(R.id.tiet_addTripActivity_destination);
        seaside = findViewById(R.id.rb_addTripActivity_seaSide);
        mountains = findViewById(R.id.rb_addTripActivity_mountains);
        citybreak = findViewById(R.id.rb_addTripActivity_cityBreak);
        initStartDatePicker();
        initEndDatePicker();
        saveButton.setOnClickListener(setOnClickListener());
        tripPrice.setOnSeekBarChangeListener(seekBarChangeListener());


    }

    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date= day+"/"+month+"/"+year;
                endDateButton.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        dateEndPickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private void initStartDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date= day+"/"+month+"/"+year;
                startDateButton.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        dateStartPickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private View.OnClickListener setOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    initTrip();
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra(RESULT_TRIPS, trips);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private boolean isValid() {
        if(tripName.getText().toString().isEmpty() || tripName.getText().toString().trim().length()<3) {
            tripName.setError(getString(R.string.invalid_trip_name));
            return false;
        }
        if(tripDestination.getText().toString().isEmpty() || tripDestination.getText().toString().trim().length()<3) {
            tripDestination.setError(getString(R.string.invalid_destination_name));
            return false;
        }
        return true;
    }

    private void initTrip() {
        tripYouHaveToEdit.setName(tripName.getText().toString());
        tripYouHaveToEdit.setDestination(tripDestination.getText().toString());
        tripYouHaveToEdit.setStartDate(DateConverter.fromString(startDateButton.getText().toString()));
        tripYouHaveToEdit.setEndDate(DateConverter.fromString(endDateButton.getText().toString()));
        tripYouHaveToEdit.setPrice(tripPrice.getProgress());
        tripYouHaveToEdit.setRate(ratingBar.getRating());
        TripType tripTypeValue = TripType.CITYBREAK;
        if(seaside.isChecked())tripTypeValue = TripType.SEASIDE;
        if(mountains.isChecked())tripTypeValue = TripType.MOUNTAINS;
        tripYouHaveToEdit.setTripType(tripTypeValue);
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


    public void onStartDateButton(View view) {
        dateStartPickerDialog.show();
    }

    public void onEndDateButton(View view) {
        dateEndPickerDialog.show();
    }
}