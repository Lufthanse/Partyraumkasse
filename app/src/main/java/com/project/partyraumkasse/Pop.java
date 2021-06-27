package com.project.partyraumkasse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Pop extends Activity {

    protected EditText name;
    protected EditText pizza;
    protected EditText extras;

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
}