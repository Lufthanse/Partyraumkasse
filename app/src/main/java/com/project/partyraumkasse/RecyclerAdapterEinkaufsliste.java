package com.project.partyraumkasse;

import androidx.recyclerview.widget.RecyclerView;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerAdapterEinkaufsliste extends RecyclerView.Adapter<RecyclerAdapterEinkaufsliste.MyViewHolder> {


    private ArrayList<Einkauf> einkaufList;

    private RecyclerAdapterEinkaufsliste.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(RecyclerAdapterEinkaufsliste.OnItemClickListener listener){
        this.listener = listener;
    }
    public RecyclerAdapterEinkaufsliste( ArrayList<Einkauf> einkaufList){
        this.einkaufList = einkaufList;
    }

    @NonNull
    @Override
    public RecyclerAdapterEinkaufsliste.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_eikaufsliste_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, listener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterEinkaufsliste.MyViewHolder holder, int position) {
        Einkauf einkauf = einkaufList.get(position);

        holder.beschreibung.setText(einkauf.getBeschreibung());
        holder.checkImage.setImageResource(R.drawable.ic_baseline_bookmark_added_24);
        holder.deleteImage.setImageResource(R.drawable.ic_delete);
    }

    @Override
    public int getItemCount() {
        return einkaufList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView beschreibung;
        ImageView deleteImage, checkImage;

        public MyViewHolder(@NonNull View itemView, RecyclerAdapterEinkaufsliste.OnItemClickListener listener) {
            super(itemView);

            beschreibung = itemView.findViewById(R.id.tv_beschreibungEL);
            deleteImage = itemView.findViewById(R.id.btn_deleteEL);
            checkImage = itemView.findViewById(R.id.btn_checkEL);

            checkImage.setOnClickListener(new View.OnClickListener() {
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
