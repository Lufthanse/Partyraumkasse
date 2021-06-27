package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pizzaliste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaliste);

    }

        public void addPizza(View view){
        startActivity(new Intent(Pizzaliste.this,Pop.class));

        }

    }

