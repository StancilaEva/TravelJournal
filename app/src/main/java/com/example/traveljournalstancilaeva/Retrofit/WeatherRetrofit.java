package com.example.traveljournalstancilaeva.Retrofit;

import android.util.Log;
import android.widget.Toast;

import com.example.traveljournalstancilaeva.BuildConfig;
import com.example.traveljournalstancilaeva.util.Weather;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRetrofit {
private static WeatherRetrofit weatherRetrofit;
private  WeatherAPI weatherAPI;
private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    private WeatherRetrofit(WeatherAPI weatherAPI){
    this.weatherAPI=weatherAPI;
}
public static WeatherRetrofit getInstance(){
    if(weatherRetrofit==null){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherRetrofit = new WeatherRetrofit(retrofit.create(WeatherAPI.class));
        weatherRetrofit.weather = new Weather();

    }
    return weatherRetrofit;
}
    public void getWeather(final OnGetWeatherCallback callback,String cityName) {
        weatherAPI.getWeather(cityName,"64c1ca0f4ce58a791e4734986367848a")
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                          //  Weather weather = response.body();
                            JsonObject jsonObject = response.body();
                            if (jsonObject != null) {
                                callback.onSuccess(jsonObject);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.i("vezi ca nu merge","of");
                    }

                });
    }
}
