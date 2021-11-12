package com.example.traveljournalstancilaeva.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip implements Parcelable {
    private String name;
    private String destination;

    public Trip(String name, String destination) {
        this.name = name;
        this.destination = destination;
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
