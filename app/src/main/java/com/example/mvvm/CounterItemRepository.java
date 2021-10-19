package com.example.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CounterItemRepository {

    private CounterItemDao counterItemDao;
    private LiveData<List<CounterItem>> allItems;

    public CounterItemRepository(Application application){
        CounterDatabase database = CounterDatabase.getInstance(application);
        counterItemDao = database.counterItemDao();
        allItems = counterItemDao.getAllCountersItems();
    }

    public void insert(CounterItem item){
        new InsertCounterItemAsyncTask(counterItemDao).execute(item);
    }

    public void update(CounterItem item){
        new UpdateCounterItemAsyncTask(counterItemDao).execute(item);
    }

    public void delete(CounterItem counterItem){
        new DeleteCounterItemAsyncTask(counterItemDao).execute(counterItem);

    }


    public LiveData<List<CounterItem>> getAllCountersItems() {
        return allItems;
    }

    public LiveData<List<CounterItem>> getAllItemsByCounter(int id){
        return counterItemDao.getCounterItems(id);
    }



    //INSERT ASYNC
    private static class InsertCounterItemAsyncTask extends AsyncTask<CounterItem, Void, Void>{
        private CounterItemDao counterItemDao;

        private InsertCounterItemAsyncTask(CounterItemDao counterItemDao){
            this.counterItemDao = counterItemDao;
        }

        @Override
        protected Void doInBackground(CounterItem... items) {
            this.counterItemDao.insert(items[0]);
            return null;
        }
    }
//
//    //UPDATE ASYNC
    private static class UpdateCounterItemAsyncTask extends AsyncTask<CounterItem, Void, Void>{
        private CounterItemDao counterItemDao;

        private UpdateCounterItemAsyncTask(CounterItemDao counterItemDao){
            this.counterItemDao = counterItemDao;
        }

        @Override
        protected Void doInBackground(CounterItem... items) {
            this.counterItemDao.update(items[0]);
            return null;
        }
    }
//
    //DELETE ASYNC
    private static class DeleteCounterItemAsyncTask extends AsyncTask<CounterItem, Void, Void>{
        private CounterItemDao counterItemDao;

        private DeleteCounterItemAsyncTask(CounterItemDao counterItemDao){
            this.counterItemDao = counterItemDao;
        }

        @Override
        protected Void doInBackground(CounterItem... items) {
            this.counterItemDao.delete(items[0]);
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
