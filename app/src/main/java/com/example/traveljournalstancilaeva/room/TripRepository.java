package com.example.traveljournalstancilaeva.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.traveljournalstancilaeva.util.Trip;

import java.util.List;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> trips;

    TripRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        tripDao = db.tripDao();
        trips = tripDao.getAllTrips();
    }

    LiveData<List<Trip>> getAllTrips() {
        return trips;
    }


    void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insert(trip);
        });
    }

    void update(Trip trip){
        TripRoomDatabase.databaseWriteExecutor.execute(()->{
            tripDao.update(trip);
        });
    }

}
