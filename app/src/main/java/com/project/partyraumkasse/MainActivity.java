package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateEinzahlung (View View){
        // HaKi 07.02.2021 16:15 - New Screen
        Intent intent = new Intent(this, Einzahlung_main.class);
        startActivity(intent);
    }

    public void navigateAuszahlung (View View){
        // HaKi 07.02.2021 16:15 - New Screen
        Intent intent = new Intent(this, Auszahlung.class);
        startActivity(intent);
    }

    public void navigateHistorie (View View){
        // HaKi 07.02.2021 16:15 - New Screen
        Intent intent = new Intent(this, Historie.class);
        startActivity(intent);
    }

    public void navigateEinkaufsliste (View View){
        // HaKi 07.02.2021 16:15 - New Screen
        Intent intent = new Intent(this, Einkaufsliste.class);
        startActivity(intent);
    }

    public void navigateKalender (View View){
        // HaKi 07.02.2021 16:15 - New Screen
        Intent intent = new Intent(this, Kalender.class);
        startActivity(intent);
    }

    public void navigatePizzaliste (View View){
        // HaKi 07.02.2021 16:15 - New Screen
        Intent intent = new Intent(this, Pizzaliste.class);
        startActivity(intent);
    }

}