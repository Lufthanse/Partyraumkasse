package com.project.partyraumkasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pizzaliste extends AppCompatActivity {
    private ArrayList<Pizza> pizzalist;
    private String name;
    private String pizza;
    private String extras;
    private Pizza pz;

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Pizza");
    private RecyclerAdapterPizzaliste adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaliste);

        recyclerView = findViewById(R.id.rv_pizzaliste);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pizzalist = new ArrayList<>();
        adapter = new RecyclerAdapterPizzaliste(pizzalist);

        recyclerView.setAdapter(adapter);

        ReadPizzaliste();
    }

        public void addPizza(View view){
        startActivity(new Intent(Pizzaliste.this,Pop.class));

        }

        public void ReadPizzaliste() {
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String nameHU = dataSnapshot.child("name").getValue(String.class);
                        String pizzaHU = dataSnapshot.child("pizza").getValue(String.class);
                        String extraHU = dataSnapshot.child("extras").getValue(String.class);
                        Pizza pizza = new Pizza(nameHU, pizzaHU, extraHU);
                        pizzalist.add(pizza);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("Schmutz", "loadPost:onCancelled", error.toException());
                }
            });

            adapter.notifyDataSetChanged();


        }



    }

