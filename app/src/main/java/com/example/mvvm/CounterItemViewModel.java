package com.example.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CounterItemViewModel extends AndroidViewModel {
    private CounterItemRepository repository;
    private CounterRepository counterRepository;
    private LiveData<List<CounterItem>> allItems;

    public CounterItemViewModel(@NonNull Application application) {
        super(application);
        repository = new CounterItemRepository(application);
        allItems = repository.getAllCountersItems();
        counterRepository = new CounterRepository(application);
    }

    public void insert(CounterItem item){
        repository.insert(item);
    }

    public void update(CounterItem item){
        repository.update(item);
    }

    public void delete(CounterItem item){
        repository.delete(item);
    }

    public LiveData<Counter> getCounterById(int id){
        return counterRepository.getCounterById(id);
    }

    public void counterUpdate(Counter counter){
        counterRepository.update(counter);
    }

    public LiveData<List<CounterItem>> getAllCountersItems(){
        return allItems;
    }

    public LiveData<List<CounterItem>> getAllItemsByCounter(int id) {
        return repository.getAllItemsByCounter(id);
    }
}
