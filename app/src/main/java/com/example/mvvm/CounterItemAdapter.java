package com.example.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CounterItemAdapter extends RecyclerView.Adapter <CounterItemAdapter.CounterItemHolder> {
    private List<CounterItem> counterItems = new ArrayList<>();

    @NonNull
    @Override
    public CounterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_counter_items, parent, false);
        return new CounterItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterItemHolder holder, int position) {
        final CounterItem currentCounterItem = counterItems.get(position);
        holder.textViewCounterName.setText(String.valueOf(currentCounterItem.getTimeString()));
        holder.textViewCounterValue.setText(String.valueOf(currentCounterItem.getValue()));
    }

    @Override
    public int getItemCount() {
        return counterItems.size();
    }

    public void setCounterItems(List<CounterItem> counterItems){
        this.counterItems = counterItems;
        notifyDataSetChanged();
    }

    public CounterItem getCounterItemAt(int index){
        return counterItems.get(index);
    }

    class CounterItemHolder extends RecyclerView.ViewHolder{
        private TextView textViewCounterName;
        private TextView textViewCounterValue;

        public CounterItemHolder(@NonNull View itemView) {
            super(itemView);
            textViewCounterName = itemView.findViewById(R.id.text_view_time);
            textViewCounterValue = itemView.findViewById(R.id.text_view_value);
        }
    }


}
