package com.example.traveljournalstancilaeva.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Trip implements Parcelable {
    private String name;
    private String destination;
    private int price;
    private double rate;
    TripType tripType;
    Date startDate;
    Date endDate;

    public Trip(String name, String destination, int price, double rate) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.rate = rate;
    }

    public Trip(String name, String destination, int price, double rate, TripType tripType, Date startDate, Date endDate) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.rate = rate;
        this.tripType = tripType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public static Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel parcel) {
            return new Trip(parcel);
        }

        @Override
        public Trip[] newArray(int i) {
            return new Trip[0];
        }
    };

    public Trip(Parcel parcel) {
        name = parcel.readString();
        destination = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(destination);
    }
}
