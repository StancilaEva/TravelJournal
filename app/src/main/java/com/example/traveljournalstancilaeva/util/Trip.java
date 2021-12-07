package com.example.traveljournalstancilaeva.util;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.traveljournalstancilaeva.room.DateRoomConverter;
import com.example.traveljournalstancilaeva.room.TripTypeConverter;

import java.util.Date;

@Entity(tableName = "Trips")
public class Trip implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Destination")
    private String destination;
    @ColumnInfo(name = "Price")
    private int price;
    @ColumnInfo(name = "Rating")
    private double rate;
    @ColumnInfo(name = "Trip_type")
    @TypeConverters(TripTypeConverter.class)
    private TripType tripType;
    @ColumnInfo(name = "Start_date")
    @TypeConverters(DateRoomConverter.class)
    private Date startDate;
    @ColumnInfo(name = "End_date")
    @TypeConverters(DateRoomConverter.class)
    private Date endDate;
    @Ignore
    private static int incrementId = 1;

    public int getId() {
        return id;
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

    public Trip() {
    }

    public Trip(Parcel parcel) {
        name = parcel.readString();
        destination = parcel.readString();
        price = parcel.readInt();
        rate = parcel.readDouble();
        String s = parcel.readString();
        switch (s) {
            case "CITYBREAK":
                tripType = TripType.CITYBREAK;
                break;
            case "SEASIDE":
                tripType = TripType.SEASIDE;
                break;
            case "MOUNTAINS":
                tripType = TripType.MOUNTAINS;
                break;
        }
        startDate = DateConverter.fromString(parcel.readString());
        endDate = DateConverter.fromString(parcel.readString());
        id = parcel.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(destination);
        parcel.writeInt(price);
        parcel.writeDouble(rate);
        parcel.writeString(tripType.name());
        parcel.writeString(DateConverter.fromDate(startDate));
        parcel.writeString(DateConverter.fromDate(endDate));
        parcel.writeInt(id);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setId(int id) {
        this.id = id;
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

}

