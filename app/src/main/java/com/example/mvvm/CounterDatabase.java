package com.example.mvvm;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Counter.class, CounterItem.class}, version = 1)
public abstract class CounterDatabase extends RoomDatabase {

    private static CounterDatabase instance;

    public abstract CounterDao counterDao();
    public abstract CounterItemDao counterItemDao();

    public static synchronized CounterDatabase getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder
                 (
                    context.getApplicationContext(),
                    CounterDatabase.class,
                    "counter_database"
                 )
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
        }

        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static  class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void>{
        private CounterDao counterDao;
        private CounterItemDao counterItemDao;

        private PopulateDbAsyncTask(CounterDatabase db){
            counterDao = db.counterDao();
            counterItemDao = db.counterItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            Counter c1 = new Counter("Counter1");
//            c1.setValue(420);
//            Counter c2 = new Counter("Counter 2");
//            c2.setValue(-420);
//            Counter c3 = new Counter("Counter 3");
//            c3.setValue(10);
//
//            counterDao.insert(c1);
//            counterDao.insert(c2);
//            counterDao.insert(c3);
//            counterDao.insert(c3);
//            counterDao.insert(c3);
//            counterDao.insert(c3);
//            counterDao.insert(c3);
//            counterDao.insert(c3);
//            counterDao.insert(c3);


            return null;
        }
    }

}
