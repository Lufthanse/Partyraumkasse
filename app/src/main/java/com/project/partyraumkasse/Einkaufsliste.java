package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Einkaufsliste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkaufsliste);
    }

    public void navigateAddEinkauf(View view){
        Intent intent = new Intent(this, AddEinkauf.class);
        startActivity(intent);
    }
}