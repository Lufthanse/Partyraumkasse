package com.project.partyraumkasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Einkaufsliste extends AppCompatActivity {
    private ArrayList<Einkauf> einkaufsliste = new ArrayList<>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Einkauf");
    private RecyclerAdapterEinkaufsliste adapterEinkaufsliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkaufsliste);

        readData(new MyCallback() {
            @Override
            public void onCallback(ArrayList<Einkauf> ek) {
                einkaufsliste = ek;
                BuildRecyclerView();
            }
        });

    }

    public void navigateAddEinkauf(View view){
        Intent intent = new Intent(this, AddEinkauf.class);
        startActivity(intent);
    }

    public void BuildRecyclerView(){

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_einkaufsliste);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterEinkaufsliste = new RecyclerAdapterEinkaufsliste(einkaufsliste);
        recyclerView.setAdapter(adapterEinkaufsliste);

        adapterEinkaufsliste.setOnItemClickListener(new RecyclerAdapterEinkaufsliste.OnItemClickListener(){
            @Override
            public void onDeleteClick(int position){
                removeItem(position);
            }

            public void onItemClick(int position){
                changeItem(position);
            }
        });
    }

    private void readData(MyCallback myCallback){
        if(einkaufsliste.isEmpty()){
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    einkaufsliste.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String beschreibung = dataSnapshot.child("beschreibung").getValue(String.class);
                        String id = dataSnapshot.getKey();
                        Einkauf ek = new Einkauf(beschreibung, id);
                        einkaufsliste.add(ek);
                    }

                    myCallback.onCallback(einkaufsliste);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("Tag", "loadPost:onCancelled", error.toException());
                }
            });

        }
    }

    public void removeItem(int position){
        Einkauf einkaufDelete = einkaufsliste.get(position);
        einkaufsliste.remove(position);
        adapterEinkaufsliste.notifyItemRemoved(position);
        DatabaseReference ekdel = FirebaseDatabase.getInstance().getReference("Einkauf").child(einkaufDelete.getId());
        ekdel.removeValue();
    }

    public interface MyCallback{
        void onCallback(ArrayList<Einkauf> ek);
    }

    public void changeItem(int position) {
        adapterEinkaufsliste.notifyItemChanged(position);
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}