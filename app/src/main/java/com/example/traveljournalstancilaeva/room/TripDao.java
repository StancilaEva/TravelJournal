package com.example.traveljournalstancilaeva.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.traveljournalstancilaeva.util.Trip;

import java.util.List;

@Dao
public interface TripDao {

    @Insert
    void insert(Trip trip);

    @Query("DELETE FROM Trips")
    void deleteAll();

    @Query("SELECT * from Trips")
    LiveData<List<Trip>> getAllTrips();

    @Update
    void update(Trip trip);


}
