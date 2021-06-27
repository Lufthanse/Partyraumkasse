package com.project.partyraumkasse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;

public class RecyclerAdapterPizzaliste extends RecyclerView.Adapter<RecyclerAdapterPizzaliste.MyViewHolder> {

    protected ArrayList<Pizza> pizzaList;

    public RecyclerAdapterPizzaliste( ArrayList<Pizza> pizzaList){
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pizza_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.name.setText(pizza.getName());
        holder.pizza.setText(pizza.getPizza());
        holder.extras.setText(pizza.getExtras());
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, pizza, extras;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            pizza = itemView.findViewById(R.id.tv_pizza);
            pizza = itemView.findViewById(R.id.tv_extras2);
        }
    }

}
