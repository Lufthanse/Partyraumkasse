package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Auszahlung_main extends AppCompatActivity {
    protected EditText name;
    protected EditText betrag;
    protected EditText grund;
    protected String nameString;
    protected String betragString;
    protected String grundString;
    protected String errorMessage;
    protected View contextView;
    protected Snackbar snackbar_vis;
    protected View snackbarView_vis;
    protected TextView textView_vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auszahlung);
        name = (EditText) findViewById(R.id.inp_name2);
        betrag = (EditText) findViewById(R.id.inp_money2);
        grund = (EditText) findViewById(R.id.inp_grund2);
    }

    public void Auszahlen(View view) {

        if (grund.getText().toString().matches("")) {
            errorMessage = "Bitte einen Grund eingeben!";
            ShowSnackbar(errorMessage);
            return;
        }


        if (name.getText().toString().matches("")) {
            errorMessage = "Bitte einen Namen eintragen!";
            ShowSnackbar(errorMessage);
            return;
        }

        if (betrag.getText().toString().matches("0") || betrag.getText().toString() == null) {
            errorMessage = "Bitte Betrag eingeben!";
            ShowSnackbar(errorMessage);
            return;
        }

        // Datenbankoperationen
        // Create
        betragString = betrag.getText().toString();
        nameString = name.getText().toString();
        grundString = grund.getText().toString();

        Auszahlung auszahlung = new Auszahlung(nameString, betragString, grundString);

        DAOAuszahlung daoAz = new DAOAuszahlung();

        daoAz.add(auszahlung).addOnSuccessListener(suc->
        {
            Toast.makeText(this, "Auszahlung gespeichert", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er->
        {
            String error = er.getMessage();
        });

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    protected void ShowSnackbar(String errorMe) {
        contextView = findViewById(android.R.id.content);
        snackbar_vis = Snackbar.make(contextView, errorMe, Snackbar.LENGTH_SHORT);
        snackbar_vis.setActionTextColor(ContextCompat.getColor(getBaseContext(), R.color.design_default_color_error));
        snackbarView_vis = snackbar_vis.getView();
        textView_vis = snackbarView_vis.findViewById(R.id.snackbar_text);
        textView_vis.setTextColor(-65536);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView_vis.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            textView_vis.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        snackbar_vis.show();

    }

}