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

public class CounterAdapter extends RecyclerView.Adapter <CounterAdapter.CounterHolder> {
    private List<Counter> counters = new ArrayList<>();
    private OnCounterClickListener listener;
    private OnCounterClickListener listenerAll;


    @NonNull
    @Override
    public CounterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.counter_item, parent, false);
        return new CounterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterHolder holder, int position) {
        final Counter currentCounter = counters.get(position);
        holder.textViewCounterName.setText(currentCounter.getName());
        holder.textViewCounterValue.setText(String.valueOf(currentCounter.getValue()));
        holder.buttonViewCounterPlus.setText("+");

    }

    @Override
    public int getItemCount() {
        return counters.size();
    }

    public void setCounters(List<Counter> counters){
        this.counters = counters;
        notifyDataSetChanged();
    }

    public Counter getCounterAt(int index){
        return counters.get(index);
    }



    class CounterHolder extends RecyclerView.ViewHolder{
        private TextView textViewCounterName;
        private TextView textViewCounterValue;
        private Button  buttonViewCounterPlus;

        public CounterHolder(@NonNull View itemView) {
            super(itemView);
            textViewCounterName = itemView.findViewById(R.id.text_view_counterName);
            textViewCounterValue = itemView.findViewById(R.id.text_view_value);
            buttonViewCounterPlus = itemView.findViewById(R.id.button_view_plus);

            buttonViewCounterPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.onCounterClick(counters.get(getAdapterPosition()));
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listenerAll != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listenerAll.onCounterClick(counters.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnCounterClickListener{
        void onCounterClick(Counter counter);
    }

    public void setOnCounterClickListener(OnCounterClickListener listener){
        this.listener = listener;
    }

    public void setOnCounterClickListener2(OnCounterClickListener listener){
        this.listenerAll = listener;
    }

}
