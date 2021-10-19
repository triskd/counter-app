package com.example.mvvm;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CounterActivity extends AppCompatActivity {
    private CounterItemViewModel counterItemViewModel;
    private CounterViewModel counterViewModel;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
//        int id = 0;

        //ziskani id counteru
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("counterId");
            //The key argument here must match that used in the other activity
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CounterItemAdapter adapter = new CounterItemAdapter();
        recyclerView.setAdapter(adapter);


        //napojit CounterItemViewModel
        counterItemViewModel = ViewModelProviders.of(this).get(CounterItemViewModel.class);
        counterItemViewModel.getAllItemsByCounter(id).observe(this,  new Observer<List<CounterItem>>() {
            @Override
            public void onChanged(List<CounterItem> counterItems) {
//                Toast.makeText(CounterActivity.this,String.valueOf(id), Toast.LENGTH_SHORT).show();
                adapter.setCounterItems(counterItems);
            }

        });

        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);

        Toast.makeText(CounterActivity.this,String.valueOf(id), Toast.LENGTH_SHORT).show();

        //delete counterItem on swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                counterItemViewModel.delete(adapter.getCounterItemAt(viewHolder.getAdapterPosition()));
                counterViewModel.decrementCounter(new Integer(id));

//                counterViewModel.getCounterById(id).observe(CounterActivity.this,  new Observer<Counter>() {
//
//                    @Override
//                    public void onChanged(Counter counter) {
////                        counter.setValue(counter.getValue()-1);
//
//                        counterViewModel.update(counter);
//                    }
//
//                });
//                Toast.makeText(CounterActivity.this, String.valueOf(counter.getId()), Toast.LENGTH_SHORT).show();
//                counter.setValue(counter.getValue()-1);
//                counterItemViewModel.counterUpdate(counter);

            }
        }).attachToRecyclerView(recyclerView);



    }
}
