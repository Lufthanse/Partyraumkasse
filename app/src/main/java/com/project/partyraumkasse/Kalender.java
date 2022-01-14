package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class Kalender extends AppCompatActivity {
    private CalendarView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        cv = findViewById(R.id.cv_calendarView);


    }

    public void addEvent(View view){
        Intent intent = new Intent(this, EventAdd.class);
        startActivity(intent);

    }

}