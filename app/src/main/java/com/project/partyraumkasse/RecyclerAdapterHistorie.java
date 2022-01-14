package com.project.partyraumkasse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.TextView;

public class RecyclerAdapterHistorie extends RecyclerView.Adapter<RecyclerAdapterHistorie.MyViewHolder>{

    private ArrayList<Zahlung> zahlungList;

    private RecyclerAdapterHistorie.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(RecyclerAdapterHistorie.OnItemClickListener listener){
        this.listener = listener;
    }

    public RecyclerAdapterHistorie( ArrayList<Zahlung> zahlungList){
        this.zahlungList = zahlungList;
    }

    @NonNull
    @Override
    public RecyclerAdapterHistorie.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_zahlung_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, listener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHistorie.MyViewHolder holder, int position) {
        Zahlung zahlung = zahlungList.get(position);

        holder.name.setText(zahlung.getName());
        holder.datum.setText(zahlung.getDatum());
        holder.uhrzeit.setText(zahlung.getUhrzeit());
        holder.grund.setText(zahlung.getGrund());
        holder.betrag.setText(zahlung.getBetrag());
        holder.zahlungsart.setText(zahlung.getZahlungsart());
    }

    @Override
    public int getItemCount() {
        return zahlungList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, datum, uhrzeit, grund, betrag, zahlungsart;

        public MyViewHolder(@NonNull View itemView, RecyclerAdapterHistorie.OnItemClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_bezeichnungE);
            datum = itemView.findViewById(R.id.tv_datumZ);
            uhrzeit = itemView.findViewById(R.id.tv_uhrzeitZ);
            grund = itemView.findViewById(R.id.tv_grundZ);
            betrag = itemView.findViewById(R.id.tv_betragZ);
            zahlungsart = itemView.findViewById(R.id.tv_zahlungsartZ);

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
