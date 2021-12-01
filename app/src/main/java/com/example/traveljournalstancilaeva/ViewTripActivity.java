package com.example.traveljournalstancilaeva;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveljournalstancilaeva.Retrofit.OnGetWeatherCallback;
import com.example.traveljournalstancilaeva.Retrofit.WeatherRetrofit;
import com.example.traveljournalstancilaeva.fragments.HomeFragment;
import com.example.traveljournalstancilaeva.util.DateConverter;
import com.example.traveljournalstancilaeva.util.Trip;
import com.example.traveljournalstancilaeva.util.Weather;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class ViewTripActivity extends AppCompatActivity {

    private TextView tvTripName;
    private TextView tvTripDestination;
    private TextView tvTripPrice;
    private TextView tvTripStartDate;
    private TextView tvTripEndDate;
    private RatingBar tvTripRating;
    private Trip trip;
    private WeatherRetrofit weatherRetrofit;
    private TextView textViewWeatherType;
    private TextView textViewWeatherDescription;
    private TextView textViewWeatherMin;
    private TextView textViewWeatherMax;
    private TextView textViewWeatherHumidity;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        initComponents();
        getInfo();
        weatherRetrofit = WeatherRetrofit.getInstance();
        weatherRetrofit.getWeather(new OnGetWeatherCallback() {
            @Override
            public void onSuccess(JsonObject jsonObject) {

                JsonArray wtherArr = jsonObject.getAsJsonArray("weather");
                JsonObject wther = (JsonObject) wtherArr.get(0);
                weatherRetrofit.getWeather().setType(wther.get("main").getAsString());
                weatherRetrofit.getWeather().setDescription(wther.get("description").getAsString());
                JsonObject temps = jsonObject.getAsJsonObject("main");
                weatherRetrofit.getWeather().setMinTemprature(temps.get("temp_min").getAsDouble());
                weatherRetrofit.getWeather().setMaxTemprature(temps.get("temp_max").getAsDouble());
                weatherRetrofit.getWeather().setHumidity(temps.get("humidity").getAsInt());
                textViewWeatherType.setText(weatherRetrofit.getWeather().getType());
                textViewWeatherDescription.setText(weatherRetrofit.getWeather().getDescription());
                textViewWeatherMin.setText(String.valueOf(weatherRetrofit.getWeather().getMinTemprature()));
                textViewWeatherMax.setText(String.valueOf(weatherRetrofit.getWeather().getMaxTemprature()));
                textViewWeatherHumidity.setText(String.valueOf(weatherRetrofit.getWeather().getHumidity()));
                switch(weatherRetrofit.getWeather().getType()){
                    case "Fog":{
                        imageView.setImageResource(R.drawable.icons8_partly_cloudy_day_48);
                        break;
                    }
                    case "Clouds":{
                        imageView.setImageResource(R.drawable.icons8_partly_cloudy_day_48);
                        break;
                    }
                    case "Rain":{
                        imageView.setImageResource(R.drawable.icons8_rain_48);
                        break;
                    }
                    case "Snow":{
                        imageView.setImageResource(R.drawable.icons8_snow_48);
                        break;
                    }
                    case "Thunderstorm":{
                        imageView.setImageResource(R.drawable.icons8_cloud_lightning_48);
                        break;
                    }
                }

            }

            @Override
            public void onError() {
            }
        },trip.getDestination());
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
        textViewWeatherType = findViewById(R.id.tv_weather_view_activity_type);
        textViewWeatherDescription= findViewById(R.id.tv_weather_view_activity_description);
       textViewWeatherMin= findViewById(R.id.tv_weather_view_activity_change_min);
        textViewWeatherMax= findViewById(R.id.tv_weather_view_activity_change_max);
         textViewWeatherHumidity = findViewById(R.id.tv_weather_view_activity_change_humidity);
         imageView = findViewById(R.id.iw_view_activity_weather_img);
    }
}