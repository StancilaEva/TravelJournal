package com.example.traveljournalstancilaeva.room;

import androidx.room.TypeConverter;

import com.example.traveljournalstancilaeva.util.TripType;

public class TripTypeConverter {
    @TypeConverter
    public static TripType toTripType(String tripType){
        if(tripType==null)return null;
        switch(tripType){
            case "MOUNTAINS":{
                return TripType.MOUNTAINS;

            }
            case "CITYBREAK":{
                return TripType.CITYBREAK;

            }
            case "SEASIDE":{
                return TripType.SEASIDE;

            }
        }
        return TripType.SEASIDE;
    }
    @TypeConverter
    public static String toString(TripType tripType){
        if(tripType==null)return null;
        switch(tripType){
            case MOUNTAINS:{
                return "MOUNTAINS";
            }
            case CITYBREAK:{
                return "CITYBREAK";
            }
            case SEASIDE:{
                return "SEASIDE";
            }
        }
        return "SEASIDE";
    }
}
