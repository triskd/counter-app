package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CounterDao {

    @Insert
    void insert(Counter counter);

    @Update
    void update(Counter counter);

    @Delete
    void delete(Counter delete);
//
//    @Query("SELECT * FROM Counter WHERE id = 'id'")
//    LiveData<Counter>getCounter(int id);

    @Query("SELECT * FROM Counter ORDER BY id")
    LiveData<List<Counter>> getAllCounters();

    @Query("SELECT * FROM Counter WHERE id = :id")
    LiveData<Counter> getCounterById(int id);

    @Query("SELECT * FROM Counter WHERE id = :id")
    Counter getCounterByIdData(int id);

    @Query("UPDATE Counter SET value = value - 1 WHERE id = :id AND value > 0")
    public void decrementCounter(int id);

}
