package com.project.partyraumkasse;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class Pop extends Activity {

    protected EditText name;
    protected EditText pizza;
    protected EditText extras;
    protected String nameString;
    protected String pizzaString;
    protected String extrasString;
    protected String errorMessage;
    protected View contextView;
    protected Snackbar snackbar_vis;
    protected View snackbarView_vis;
    protected TextView textView_vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_pl);

        name = (EditText) findViewById(R.id.inp_namePL);
        pizza = (EditText) findViewById(R.id.inp_pizzaPL);
        extras = (EditText) findViewById(R.id.inp_extrasPL);

    }

    public void showextras(View view) {

        if (extras.getVisibility() == View.INVISIBLE) {
            extras.setVisibility(View.VISIBLE);
        } else {
            extras.setVisibility(View.INVISIBLE);
            extras.setText("");

        }
    }

    public void addPizzas(View view){

        if (name.getText().toString().matches("")){
            errorMessage = "Bitte Name eintragen!";
            ShowSnackbar(errorMessage);
            return;
        }

        if (pizza.getText().toString().matches((""))){
            errorMessage = "Bitte Pizza eintragen!";
            ShowSnackbar(errorMessage);
            return;
        }

        if (extras.getVisibility() == View.VISIBLE){
            if(extras.getText().toString().matches("")){
                errorMessage = "Bei Extrawunsch bitte Extras eintragen!";
                ShowSnackbar(errorMessage);
                return;
            }
        }

        //Datenbankoperationen
        //Create

        nameString = name.getText().toString();
        pizzaString = pizza.getText().toString();
        extrasString = extras.getText().toString();

        Pizza pizza = new Pizza(nameString, pizzaString, extrasString);

        DAOPizza dao = new DAOPizza();
        dao.add(pizza).addOnSuccessListener(suc->
        {
            Toast.makeText(this, "Pizza gespeichert", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er->
        {
            Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT);
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