package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
//    public static final int COUNTER_ACTIVITY = 2;
    private CounterViewModel counterViewModel;
    private CounterItemViewModel counterItemViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open add_note activity
        FloatingActionButton buttonAddCounter = findViewById(R.id.button_add_counter);
        buttonAddCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCounterActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CounterAdapter adapter = new CounterAdapter();
        recyclerView.setAdapter(adapter);

        //napojit CounterViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        counterViewModel.getAllCounters().observe(this, new Observer<List<Counter>>() {
            @Override
            public void onChanged(List<Counter> counters) {
                //update RecyclerView
                adapter.setCounters(counters);
//                Toast.makeText(MainActivity.this,"hola", Toast.LENGTH_SHORT).show();

            }
        });

        //napojit CounterItemViewModel
        counterItemViewModel = ViewModelProviders.of(this).get(CounterItemViewModel.class);
        counterItemViewModel.getAllCountersItems().observe(this,  new Observer<List<CounterItem>>() {
            @Override
            public void onChanged(List<CounterItem> counterItems) {
               // Toast.makeText(MainActivity.this,"hola", Toast.LENGTH_SHORT).show();
            }

        });

        //delete counter on swipe right
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                counterViewModel.delete(adapter.getCounterAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"counter deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //reseted counter on swipe left
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Counter counter = adapter.getCounterAt(viewHolder.getAdapterPosition());
                counter.setValue(0);
                counterViewModel.update(counter);

                CounterItem item = new CounterItem(counter.getId(), 0);
                counterItemViewModel.insert(item);

                Toast.makeText(MainActivity.this,"counter reseted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //increment value
        adapter.setOnCounterClickListener(new CounterAdapter.OnCounterClickListener() {
            @Override
            public void onCounterClick(Counter counter) {
                counter.setValue(counter.getValue()+1);
                counterViewModel.update(counter);

                CounterItem newItem = new CounterItem(counter.getId(), counter.getValue());
                counterItemViewModel.insert(newItem);
//                Toast.makeText(MainActivity.this, newItem.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // open counter activity pass counterId to new activity
        adapter.setOnCounterClickListener2(new CounterAdapter.OnCounterClickListener() {
            @Override
            public void onCounterClick(Counter counter) {
                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                intent.putExtra("counterId", counter.getId());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddCounterActivity.EXTRA_NAME);

            Counter counter = new Counter(name);
            counterViewModel.insert(counter);

            Toast.makeText(this, "Counter saved", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, "Counter NOT saved", Toast.LENGTH_SHORT).show();
        }
    }
}
