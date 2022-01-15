package com.project.partyraumkasse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterEvents extends RecyclerView.Adapter<RecyclerAdapterEvents.MyViewHolder>{

    private ArrayList<Event>eventList;

    private RecyclerAdapterEvents.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(RecyclerAdapterEvents.OnItemClickListener listener){
        this.listener = listener;
    }

    public RecyclerAdapterEvents(ArrayList<Event> eventList){
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public RecyclerAdapterEvents.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, listener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterEvents.MyViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.event.setText(event.getBezeichnung());
        holder.datum.setText(event.getDatum());
        holder.uhrzeit.setText(event.getUhrzeit());
        holder.location.setText(event.getOrt());
        holder.hinweis.setText(event.getHinweise());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView event, datum, uhrzeit, location, hinweis;
        ImageView deleteImage;

        public MyViewHolder(@NonNull View itemView, RecyclerAdapterEvents.OnItemClickListener listener) {
            super(itemView);

            event = itemView.findViewById(R.id.tv_bezeichnungEv);
            datum = itemView.findViewById(R.id.tv_datumEv);
            uhrzeit = itemView.findViewById(R.id.tv_uhrzeitEv);
            location = itemView.findViewById(R.id.tv_ortEv);
            hinweis = itemView.findViewById(R.id.tv_hinweisEv);
            deleteImage = itemView.findViewById(R.id.btn_deleteEv);

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
