package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Einzahlung_main extends AppCompatActivity {
    protected EditText name;
    protected EditText geld;
    protected EditText grund;
    protected String errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einzahlung_main);
        grund = (EditText) findViewById(R.id.inp_grund);
        geld = (EditText) findViewById(R.id.inp_money);
        name = (EditText) findViewById(R.id.inp_name);
    }

    public void addGetraenk(View view){

        if (geld.getText().toString().matches("")){
            geld.setText("0");
        }
        double money = Double.parseDouble(geld.getText().toString());
        money += 1;
        String moneyS = String.valueOf(money);
        geld.setText(moneyS);
    }

    public void addFlat(View view){

        if (geld.getText().toString().matches("")){
            geld.setText("0");
        }
        double money = Double.parseDouble(geld.getText().toString());
        money += 5;
        String moneyS = String.valueOf(money);
        geld.setText(moneyS);

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
                View contextView = findViewById(android.R.id.content);
                Snackbar snackbar_vis = Snackbar.make(contextView, errorMessage, Snackbar.LENGTH_SHORT);
                snackbar_vis.setActionTextColor(ContextCompat.getColor(getBaseContext(), R.color.design_default_color_error));
                View snackbarView_vis = snackbar_vis.getView();
                TextView textView_vis = snackbarView_vis.findViewById(R.id.snackbar_text);
                textView_vis.setTextColor(-65536);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    textView_vis.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    textView_vis.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                snackbar_vis.show();
                //test
            }
        }

    }

}