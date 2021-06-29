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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pizzaliste extends AppCompatActivity {
    private ArrayList<Pizza> pizzalist = new ArrayList<>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Pizza");
    private RecyclerAdapterPizzaliste adapterPizzaliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaliste);

        readData(new MyCallback() {
            @Override
            public void onCallback(ArrayList<Pizza> pl) {
                pizzalist = pl;
                BuildRecyclerView();
            }
        });

    }

        public void addPizza(View view){
        startActivity(new Intent(Pizzaliste.this,Pop.class));

        }

        public void BuildRecyclerView(){

            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_pizzaliste);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            ArrayList<Pizza> pizzaListDUP = new ArrayList<>();
            for (Pizza pz : pizzalist){
                if (!pizzaListDUP.contains(pz)){
                    pizzaListDUP.add(pz);
                }
            }


            adapterPizzaliste = new RecyclerAdapterPizzaliste(pizzalist);
            recyclerView.setAdapter(adapterPizzaliste);

            adapterPizzaliste.setOnItemClickListener(new RecyclerAdapterPizzaliste.OnItemClickListener(){
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
            if(pizzalist.isEmpty()){
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String pizza = dataSnapshot.child("pizza").getValue(String.class);
                        String extra = dataSnapshot.child("extras").getValue(String.class);
                        String id = dataSnapshot.getKey();
                        Pizza pizzas = new Pizza(name, pizza, extra, id);
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
        }

        public interface MyCallback{
            void onCallback(ArrayList<Pizza> pl);
        }

        public void removeItem(int position){
            Pizza pizzaDelete = pizzalist.get(position);
            pizzalist.remove(position);
            adapterPizzaliste.notifyItemRemoved(position);
            DatabaseReference pizdel = FirebaseDatabase.getInstance().getReference("Pizza").child(pizzaDelete.getId());
            pizdel.removeValue();
        }

        public void changeItem(int position) {
            adapterPizzaliste.notifyItemChanged(position);
        }

    }

