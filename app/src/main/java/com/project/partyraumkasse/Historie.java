package com.project.partyraumkasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Historie extends AppCompatActivity {
    private ArrayList<Zahlung> zahlungList = new ArrayList<Zahlung>();
    private ArrayList<Zahlung> zahlungList1 = new ArrayList<Zahlung>();
    private ArrayList<Zahlung> zahlungList2 = new ArrayList<Zahlung>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference ez = db.getReference().child("PK/Einzahlung");
    private DatabaseReference az = db.getReference().child("PK/Auszahlung");
    private RecyclerAdapterHistorie adapterHistorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historie);

        readDataEZ(new Historie.MyCallback() {
            @Override
            public void onCallback(ArrayList<Zahlung> z1, ArrayList<Zahlung> z2) {
                zahlungList.addAll(z1);
                zahlungList.addAll(z2);
                BuilderRecyclerView();
                zahlungList.sort(new Comparator<Zahlung>() {
                    public int compare(Zahlung o1, Zahlung o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });
            }
        });

    }

    private void readDataEZ(Historie.MyCallback myCallback){

        ez.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                zahlungList1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String betragEz = dataSnapshot.child("betrag").getValue(String.class) + "€";
                    String nameEz = dataSnapshot.child("name").getValue(String.class);
                    String datumEz = dataSnapshot.child("datum").getValue(String.class);
                    String uhrzeitEz = dataSnapshot.child("uhrzeit").getValue(String.class);
                    String grundEz = dataSnapshot.child("grund").getValue(String.class);
                    Zahlung zahlung = new Zahlung(nameEz, betragEz, grundEz, datumEz, uhrzeitEz, "Einzahlung");
                    zahlungList1.add(zahlung);
                }

                readDataAZ(new Historie.SecondCallback() {
                    @Override
                    public void onCallback(ArrayList<Zahlung> z2) {
                        zahlungList2 = z2;
                        myCallback.onCallback(zahlungList1, zahlungList2);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag", "loadPost:onCancelled", error.toException());
            }
        });

    }

    public void readDataAZ(Historie.SecondCallback myCallback){
        az.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                zahlungList2.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String betragAz = dataSnapshot.child("betrag").getValue(String.class) + "€";
                    String nameAz = dataSnapshot.child("name").getValue(String.class);
                    String datumAz = dataSnapshot.child("datum").getValue(String.class);
                    String uhrzeitAz = dataSnapshot.child("uhrzeit").getValue(String.class);
                    String grundAz = dataSnapshot.child("grund").getValue(String.class);
                    Zahlung zahlung = new Zahlung(nameAz, betragAz, grundAz, datumAz, uhrzeitAz, "Auszahlung");
                    zahlungList2.add(zahlung);
                }

                myCallback.onCallback(zahlungList2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag", "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void BuilderRecyclerView(){

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_historie);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterHistorie = new RecyclerAdapterHistorie(zahlungList);
        recyclerView.setAdapter(adapterHistorie);

    }

    public interface MyCallback{
        void onCallback(ArrayList<Zahlung> zahlung, ArrayList<Zahlung> zahlung2);
    }

    public interface SecondCallback{
        void onCallback(ArrayList<Zahlung> zahlung);
    }

}