package com.example.traveljournalstancilaeva.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveljournalstancilaeva.util.Trip;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository tripRepository;

    private LiveData<List<Trip>> trips;


    public void insert(Trip trip) {
        tripRepository.insert(trip);
    }

    public void update(Trip trip){
        tripRepository.update(trip);
    }

    public void delete(Trip trip){ tripRepository.delete(trip);}

    public TripViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
        trips = tripRepository.getAllTrips();
    }

   public LiveData<List<Trip>> getAllTrips() { return trips; }
}
