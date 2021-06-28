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
    private ArrayList<Pizza> pizzalist = new ArrayList<>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Pizza");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaliste);

        readData(new MyCallback() {
            @Override
            public void onCallback(ArrayList<Pizza> pl) {
                pizzalist = pl;
                Log.d("TAG", pl.toString());
                BuildRecyclerView();
            }
        });

    }

        public void addPizza(View view){
        startActivity(new Intent(Pizzaliste.this,Pop.class));

        }

        public void ReadPizzaliste() {

        }

        public void BuildRecyclerView(){

            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_pizzaliste);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            RecyclerAdapterPizzaliste adapterPizzaliste = new RecyclerAdapterPizzaliste(pizzalist);
            recyclerView.setAdapter(adapterPizzaliste);

        }

        private void readData(MyCallback myCallback){
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String pizza = dataSnapshot.child("pizza").getValue(String.class);
                        String extra = dataSnapshot.child("extras").getValue(String.class);
                        Pizza pizzas = new Pizza(name, pizza, extra);
                        pizzalist.add(pizzas);
                    }

                    myCallback.onCallback(pizzalist);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("Tag", "loadPost:onCancelled", error.toException());
                }
            });
        }

        public interface MyCallback{
            void onCallback(ArrayList<Pizza> pl);
        }


    }

