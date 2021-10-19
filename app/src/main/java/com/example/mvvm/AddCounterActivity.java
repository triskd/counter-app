package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCounterActivity extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.examplme.mvvm.EXTRA_NAME";

    private EditText editTextName;
    private Button buttonAddCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        editTextName = findViewById(R.id.edit_text_name);
        buttonAddCounter = findViewById(R.id.button_add_counter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add counter");

        buttonAddCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCounter();
            }
        });
    }

    private void saveCounter(){
        String name = editTextName.getText().toString();

        if(name.trim().isEmpty()){
            Toast.makeText(this, "Please insert name of the counter", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_counter_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_counter:
                saveCounter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


