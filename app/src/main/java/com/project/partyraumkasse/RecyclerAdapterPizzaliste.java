package com.project.partyraumkasse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapterPizzaliste extends RecyclerView.Adapter<RecyclerAdapterPizzaliste.MyViewHolder> {

    private ArrayList<Pizza> pizzaList;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public RecyclerAdapterPizzaliste( ArrayList<Pizza> pizzaList){
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pizza_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, listener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.name.setText(pizza.getName());
        holder.pizza.setText(pizza.getPizza());
        holder.extras.setText(pizza.getExtras());
        //holder.deleteImage.setImageResource(R.drawable.ic_delete);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, pizza, extras;
        ImageView deleteImage;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_bezeichnungEv);
            pizza = itemView.findViewById(R.id.tv_pizza);
            extras = itemView.findViewById(R.id.tv_extras2);
            deleteImage = itemView.findViewById(R.id.btn_deletePL);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION ){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            deleteImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


        }

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

}
