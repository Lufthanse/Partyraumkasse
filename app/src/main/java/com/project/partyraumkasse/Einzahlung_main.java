package com.project.partyraumkasse;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Einzahlung_main extends AppCompatActivity {
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
        setContentView(R.layout.activity_einzahlung_main);
        grund = (EditText) findViewById(R.id.inp_grund);
        betrag = (EditText) findViewById(R.id.inp_money);
        name = (EditText) findViewById(R.id.inp_name);

    }

    public void addGetraenk(View view){

        if (betrag.getText().toString().matches("")){
            betrag.setText("0");
        }
        double money = Double.parseDouble(betrag.getText().toString());
        money += 1;
        String moneyS = String.valueOf(money);
        betrag.setText(moneyS);
    }

    public void addFlat(View view){

        if (betrag.getText().toString().matches("")){
            betrag.setText("0");
        }
        double money = Double.parseDouble(betrag.getText().toString());
        money += 5;
        String moneyS = String.valueOf(money);
        betrag.setText(moneyS);

    }

    public void showGrund(View view){

        if (grund.getVisibility() == View.INVISIBLE ){
            grund.setVisibility(View.VISIBLE);
        } else {
            grund.setVisibility(View.INVISIBLE);
            grund.setText("");
        }
    }

    public void Einzahlen(View view){

        if (grund.getVisibility() == View.VISIBLE){
            if(grund.getText().toString().matches("")){
                errorMessage = "Bei sonstiger Zahlung bitte Grund eingeben!";
                ShowSnackbar(errorMessage);
                return;
            }
        }

        if(name.getText().toString().matches("")){
            errorMessage = "Bitte einen Namen eintragen!";
            ShowSnackbar(errorMessage);
            return;
        }

        if(betrag.getText().toString().matches("0") || betrag.getText().toString() == null){
            errorMessage = "Bitte Betrag eingeben!";
            ShowSnackbar(errorMessage);
            return;
        }

        // Datenbankoperationen
        // Create
        betragString = betrag.getText().toString();
        nameString = name.getText().toString();
        grundString = grund.getText().toString();

        Einzahlung einzahlung = new Einzahlung(nameString, betragString, grundString);

        DAOEinzahlung dao = new DAOEinzahlung();
        dao.add(einzahlung).addOnSuccessListener(suc->
                {
                    Toast.makeText(this, "Einzahlung gespeichert", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT);
                });

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    protected void ShowSnackbar(String errorMe){
        contextView = findViewById(android.R.id.content);
        snackbar_vis = Snackbar.make(contextView, errorMe, Snackbar.LENGTH_SHORT);
        snackbar_vis.setActionTextColor(ContextCompat.getColor(getBaseContext(), R.color.design_default_color_error));
        snackbarView_vis = snackbar_vis.getView();
        textView_vis = snackbarView_vis.findViewById(R.id.snackbar_text);
        textView_vis.setTextColor(-65536);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            textView_vis.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            textView_vis.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        snackbar_vis.show();

    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}