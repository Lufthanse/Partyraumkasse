package com.project.partyraumkasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView kassenstand;
    private String kassenstandString;
    private ArrayList<String> einzahlungen = new ArrayList<>();
    private ArrayList<String> auszahlungen = new ArrayList<>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference einzahlungRoot = db.getReference().child("PK/Einzahlung");
    private DatabaseReference auszahlungRoot = db.getReference().child("PK/Auszahlung");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kassenstand = (TextView) findViewById(R.id.tv_kassenstand);

        readDataEZ(new MainActivity.MyCallback() {
            @Override
            public void onCallback(ArrayList<String> ez, ArrayList<String> az) {
                einzahlungen = ez;
                auszahlungen = az;
                kassenstand.setText(calculateKassenstand(einzahlungen, auszahlungen) +" €");
            }
        });

    }

    public void navigateEinzahlung (View View){
        Intent intent = new Intent(this, Einzahlung_main.class);
        startActivity(intent);
    }

    public void navigateAuszahlung (View View){
        Intent intent = new Intent(this, Auszahlung_main.class);
        startActivity(intent);
    }

    public void navigateHistorie (View View){
        Intent intent = new Intent(this, Historie.class);
        startActivity(intent);
    }

    public void navigateEinkaufsliste (View View){
        Intent intent = new Intent(this, Einkaufsliste.class);
        startActivity(intent);
    }

    public void navigateKalender (View View){
        Intent intent = new Intent(this, Kalender.class);
        startActivity(intent);
    }

    public void navigatePizzaliste (View View){
        Intent intent = new Intent(this, Pizzaliste.class);
        startActivity(intent);
    }

    private void readDataEZ(MainActivity.MyCallback myCallback){

        einzahlungRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                einzahlungen.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String betrag = dataSnapshot.child("betrag").getValue(String.class);
                    einzahlungen.add(betrag);
                }

                readDataAZ(new MainActivity.SecondCallback() {
                    @Override
                    public void onCallback(ArrayList<String> ausz) {
                        auszahlungen = ausz;
                        myCallback.onCallback(einzahlungen, auszahlungen);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag", "loadPost:onCancelled", error.toException());
            }
        });

    }

    public void readDataAZ(MainActivity.SecondCallback myCallback){
        auszahlungRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                auszahlungen.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String betrag = dataSnapshot.child("betrag").getValue(String.class);
                    auszahlungen.add(betrag);
                }

                myCallback.onCallback(auszahlungen);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag", "loadPost:onCancelled", error.toException());
            }
        });
    }

    public String calculateKassenstand(ArrayList<String> ez, ArrayList<String> az){
        Double einzahlungen = 0.00;
        Double auszahlungen = 0.00;
        Double ergebnis = 0.00;
        for (String betrag : ez){
            einzahlungen = einzahlungen + Double.parseDouble(betrag);
        }

        for (String betrag : az){
            auszahlungen = auszahlungen + Double.parseDouble(betrag);
        }

        ergebnis = einzahlungen - auszahlungen;

        String ergeb = String.format("%.2f", ergebnis);

        return ergeb;
    }

    public interface MyCallback{
        void onCallback(ArrayList<String> ez, ArrayList<String> az);
    }

    public interface SecondCallback{
        void onCallback(ArrayList<String> ausz);
    }

    public void openHype(View view){
        Intent intent = new Intent(this, Hype.class);
        startActivity(intent);
    }


}