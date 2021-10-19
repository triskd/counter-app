package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CounterItemDao {


    @Insert
    void insert(CounterItem counterItem);

    @Update
    void update(CounterItem counterItem);

    @Delete
    void delete(CounterItem counterItem);
//
    @Query("SELECT * FROM CounterItem WHERE counterId = :id ORDER BY id DESC")
    LiveData<List<CounterItem>>getCounterItems(int id);

    @Query("SELECT * FROM CounterItem ORDER BY counterId")
    LiveData<List<CounterItem>> getAllCountersItems();



}
