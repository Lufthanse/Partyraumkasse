package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.jar.Attributes;

public class Pizzaliste extends AppCompatActivity {

    protected EditText Name;
    protected EditText Pizza;
    protected EditText Extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pizzaliste);
        Name = (EditText) findViewById(R.id.inp_namePL);
        Pizza = (EditText) findViewById(R.id.inp_pizza);
        Extras = (EditText) findViewById(R.id.inp_Extras);

    }    public void showExtras(View view) {

        if (Extras.getVisibility() == View.INVISIBLE) {
            Extras.setVisibility(View.VISIBLE);
        } else {
            Extras.setVisibility(View.INVISIBLE);
            Extras.setText("");
        }
    }
}