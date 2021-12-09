package com.example.traveljournalstancilaeva.Retrofit;

import com.example.traveljournalstancilaeva.util.Weather;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("/data/2.5/weather")
    Call<JsonObject> getWeather(@Query("q") String city_name, @Query("appid") String keyAPI);
}
