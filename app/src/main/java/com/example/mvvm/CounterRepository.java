package com.example.mvvm;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CounterRepository {

    private CounterDao counterDao;
    private LiveData<List<Counter>> allCounters;

    public CounterRepository(Application application){
        CounterDatabase database = CounterDatabase.getInstance(application);
        counterDao = database.counterDao();
        allCounters = counterDao.getAllCounters();
    }

    public void insert(Counter counter){
        new InsertCounterAsyncTask(counterDao).execute(counter);
    }

    public void update(Counter counter){
        new UpdateCounterAsyncTask(counterDao).execute(counter);
    }

    public void delete(Counter counter){
        new DeleteCounterAsyncTask(counterDao).execute(counter);
    }

    public void decrementCounter(Integer id){ new DecrementCounterAsyncTask(counterDao).execute(id);}

//    public Counter getCounterByIdAsync(int id){
//       GetCounterAsyncTask task =  new GetCounterAsyncTask(counterDao);
//        task.execute(id);
//        Counter c = task.getCounter();
//        Log.i("ss", c.getName());
//        return c;
//    }

    public LiveData<Counter> getCounterById(int id){
        return counterDao.getCounterById(id);
    }

    public LiveData<List<Counter>> getAllCounters() {
        return allCounters;
    }

    public Counter getCounterByIdData(int id) {
        return counterDao.getCounterByIdData(id);
    }

    //INSERT ASYNC
    private static class InsertCounterAsyncTask extends AsyncTask<Counter, Void, Void>{
        private CounterDao counterDao;

        private InsertCounterAsyncTask(CounterDao counterDao){
            this.counterDao = counterDao;
        }

        @Override
        protected Void doInBackground(Counter... counters) {
            this.counterDao.insert(counters[0]);
            return null;
        }
    }

    //UPDATE ASYNC
    private static class UpdateCounterAsyncTask extends AsyncTask<Counter, Void, Void>{
        private CounterDao counterDao;

        private UpdateCounterAsyncTask(CounterDao counterDao){
            this.counterDao = counterDao;
        }

        @Override
        protected Void doInBackground(Counter... counters) {
            this.counterDao.update(counters[0]);
            return null;
        }
    }

    //DELETE ASYNC
    private static class DeleteCounterAsyncTask extends AsyncTask<Counter, Void, Void>{
        private CounterDao counterDao;

        private DeleteCounterAsyncTask(CounterDao counterDao){
            this.counterDao = counterDao;
        }

        @Override
        protected Void doInBackground(Counter... counters) {
            this.counterDao.delete(counters[0]);
            return null;
        }
    }

    // DECREMENT COUNTER ASYNC
    private static class DecrementCounterAsyncTask extends AsyncTask<Integer, Void, Void>{
        private CounterDao counterDao;

        private DecrementCounterAsyncTask(CounterDao counterDao){
            this.counterDao = counterDao;
        }

        @Override
        protected Void doInBackground(Integer ... integers) {
            counterDao.decrementCounter(integers[0]);
            return null;
        }

    }


    //DELETE ALL ASYNC
//    private static class DeleteAllNotesCounterAsyncTask extends AsyncTask<Void, Void, Void>{
//        private CounterDao counterDao;
//
//        private UpdateCounterAsyncTask(Void... voids){
//            this.counterDao = counterDao;
//        }
//
//        @Override
//        protected Void doInBackground(Counter... counters) {
//            this.counterDao.deleteAllCounters(counters[0]);
//            return null;
//        }
//    }
}
