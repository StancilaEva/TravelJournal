package com.example.traveljournalstancilaeva.util;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("main")
    private String type;
    @SerializedName("description")
    private String description;
    @SerializedName("temp_min")
    private double minTemprature;
    @SerializedName("temp_max")
    private double maxTemprature;
    @SerializedName("humidity")
    private int humidity;

    public Weather() {
    }

    @Override
    public String toString() {
        return "Weather{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", minTemprature=" + minTemprature +
                ", maxTemprature=" + maxTemprature +
                ", humidity=" + humidity +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinTemprature() {
        return minTemprature;
    }

    public void setMinTemprature(double minTemprature) {
        this.minTemprature = minTemprature;
    }

    public double getMaxTemprature() {
        return maxTemprature;
    }

    public void setMaxTemprature(double maxTemprature) {
        this.maxTemprature = maxTemprature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
