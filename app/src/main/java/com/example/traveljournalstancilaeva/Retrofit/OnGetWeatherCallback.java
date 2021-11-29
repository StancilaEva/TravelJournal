package com.example.traveljournalstancilaeva.Retrofit;

import com.example.traveljournalstancilaeva.util.Weather;
import com.google.gson.JsonObject;

public interface OnGetWeatherCallback {
    void onSuccess(JsonObject jsonObject);
    void onError();
}
