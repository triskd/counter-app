package com.example.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CounterViewModel extends AndroidViewModel {
    private CounterRepository repository;
    private LiveData<List<Counter>> allCounters;

    public CounterViewModel(@NonNull Application application) {
        super(application);
        repository = new CounterRepository(application);
        allCounters = repository.getAllCounters();
    }

    public void insert(Counter counter){
        repository.insert(counter);
    }

    public void update(Counter counter){
        repository.update(counter);
    }

    public void delete(Counter counter){
        repository.delete(counter);
    }

    public void decrementCounter(Integer id){ repository.decrementCounter(id); }

    public LiveData<Counter> getCounterById(int id){
        return repository.getCounterById(id);
    }

    public Counter getCounterByIdData(int id){
        return repository.getCounterByIdData(id);
    }



    public LiveData<List<Counter>> getAllCounters() {
        return allCounters;
    }
}
