package com.example.mvvm;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "CounterItem",
        foreignKeys = @ForeignKey(entity = Counter.class,
        parentColumns = "id",
        childColumns = "counterId",
        onDelete = CASCADE),
        indices = {@Index(value={"id"}, unique = true)}
)
public class CounterItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int counterId;
    private long time = new Date().getTime();
    private int value;

    public CounterItem(int counterId, int value) {
        this.counterId = counterId;
        this.value = value;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTime(long time){
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getCounterId() {
        return counterId;
    }

    public long getTime() {
        return time;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "id: " + this.getId() + ", counterId: " + this.counterId + ", value: " + this.value + ", date: " + new Date(this.time);
    }

    public String getTimeString(){
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);

        Date today = new Date(time);
        String todayAsString = df.format(today);


        return todayAsString;
    }

}

