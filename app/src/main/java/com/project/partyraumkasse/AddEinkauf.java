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

public class AddEinkauf extends AppCompatActivity {
    protected EditText beschreibung;
    protected String beschreibungString;
    protected String errorMessage;
    protected View contextView;
    protected Snackbar snackbar_vis;
    protected View snackbarView_vis;
    protected TextView textView_vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_einkauf);
        beschreibung = (EditText) findViewById(R.id.inp_beschreibung);
    }

    public void addEinkauf(View view){

        if (beschreibung.getText().toString().matches("")){
            errorMessage = "Bitte eine Beschreibung eintragen!";
            ShowSnackbar(errorMessage);
            return;
        }

        beschreibungString = beschreibung.getText().toString();
        Einkauf einkauf = new Einkauf(beschreibungString);

        DAOEinkauf daoEk = new DAOEinkauf();

        daoEk.add(einkauf).addOnSuccessListener(suc->
        {
            Toast.makeText(this, "Einkauf gespeichert", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er->
        {
            String error = er.getMessage();
        });

        Intent intent = new Intent(this, Einkaufsliste.class);
        startActivity(intent);

    }

    public void ShowSnackbar(String errorMe){
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

    public void onBackPressed(){
        Intent intent = new Intent(this, Einkaufsliste.class);
        startActivity(intent);
    }

}