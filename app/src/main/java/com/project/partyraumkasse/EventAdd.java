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

public class EventAdd extends AppCompatActivity {

    public EditText event;
    public EditText datum;
    public EditText uhrzeit;
    public EditText location;
    public EditText hinweis;
    public String eventString;
    public String datumString;
    public String uhrzeitString;
    public String locationString;
    public String hinweisString;
    protected String errorMessage;
    protected View contextView;
    protected Snackbar snackbar_vis;
    protected View snackbarView_vis;
    protected TextView textView_vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        event = (EditText) findViewById(R.id.inp_eventEv);
        datum = (EditText) findViewById(R.id.inp_datumEv);
        uhrzeit = (EditText) findViewById(R.id.inp_uhrzeitEv);
        location = (EditText) findViewById(R.id.inp_OrtEv);
        hinweis = (EditText) findViewById(R.id.inp_hinweiseEv);

    }

    public void addEvent(View view){

        if (event.getText().toString().matches("")){
            errorMessage = "Bitte Event eintragen!";
            ShowSnackbar(errorMessage);
            return;
        }

        if (datum.getText().toString().matches("")) {
            errorMessage = "Bitte Event eintragen!";
            ShowSnackbar(errorMessage);
        }

        if (uhrzeit.getText().toString().matches("")) {
            errorMessage = "Bitte Event eintragen!";
            ShowSnackbar(errorMessage);
        }

        if (location.getText().toString().matches("")) {
            errorMessage = "Bitte Event eintragen!";
            ShowSnackbar(errorMessage);
        }

        if (hinweis.getText().toString().matches("")) {
            errorMessage = "Bitte Event eintragen!";
            ShowSnackbar(errorMessage);
        }

        //Datenbankoperationen
        //Create

        eventString = event.getText().toString();
        datumString = datum.getText().toString();
        uhrzeitString = uhrzeit.getText().toString();
        locationString = location.getText().toString();
        hinweisString = location.getText().toString();

        Event ev = new Event(eventString, datumString, uhrzeitString, locationString, hinweisString);

        DAOEvent dao = new DAOEvent();
        dao.add(ev).addOnSuccessListener(suc->
        {
            Toast.makeText(this, "Event gespeichert", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er->
        {
            Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT);
        });

        Intent intent = new Intent(this, Kalender.class);
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