package com.example.mvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Counter")
public class Counter {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int value;

    public Counter(String name) {
        this.name = name;
        this.value = 0;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
