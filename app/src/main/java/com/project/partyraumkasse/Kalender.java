package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

public class Kalender extends AppCompatActivity {
    private CalendarView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        cv = findViewById(R.id.cv_calendarView);


    }
}