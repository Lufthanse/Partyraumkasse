package com.project.partyraumkasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Kalender extends AppCompatActivity {
    public CompactCalendarView cv;
    public TextView monat;
    private ArrayList<Event> eventlist = new ArrayList<>();
    private ArrayList<Event> deleteevetlist = new ArrayList<Event>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("PK/Event");
    private RecyclerAdapterEvents adapterEventliste;
    public Date dateev;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        monat = (TextView) findViewById(R.id.tv_EventHeader);

        cv = findViewById(R.id.compactcalendar_view);
        cv.setUseThreeLetterAbbreviation(true);

        cv.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                eventlist.clear();
                readData(new MyCallback() {
                    @Override
                    public void onCallback(ArrayList<Event> ev) {
                        eventlist = ev;
                        // Datumsvergleich ----
                        for(Event event : eventlist){
                            DateFormat formatter = new SimpleDateFormat("dd.MM.YYYY");
                            String formattedDate = formatter.format(dateClicked);
                            if (formattedDate.equals(event.getDatum())){
                            }else{
                                deleteevetlist.add(event);
                            }
                        }

                        eventlist.removeAll(deleteevetlist);

                        // Ende Datumsvergleich
                        BuildRecyclerView();
                    }
                });
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Calendar cal = Calendar.getInstance();
                Date d = cv.getFirstDayOfCurrentMonth();
                cal.setTime(d);
                int year = cal.get(Calendar.YEAR);
                String yearString = Integer.toString(year);
                int monthday = cal.get(Calendar.MONTH);
                String monthString = getMonth(monthday);
                String headerString = monthString + " " + yearString;
                monat.setText(headerString);
            }
        });

        readData(new MyCallback() {
            @Override
            public void onCallback(ArrayList<Event> ev) {
                eventlist = ev;

                // Event setzen
                for(Event event : eventlist){

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String dateString = event.getDatum() + " " + event.getUhrzeit();
                    dateString = dateString.replace('.', '/');
                    Date date = new Date();

                    try {
                        date = formatter.parse(dateString);
                    }catch(ParseException ex) {

                    }

                    long millis = date.getTime();
                    cv.addEvent(new com.github.sundeepk.compactcalendarview.domain.Event(Color.RED, millis));
                }

                eventlist.clear();
                BuildRecyclerView();
            }
        });


    }

    public void addEvent(View view){
        Intent intent = new Intent(this, EventAdd.class);
        startActivity(intent);

    }

    public void BuildRecyclerView(){

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_eventListe);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterEventliste = new RecyclerAdapterEvents(eventlist);
        recyclerView.setAdapter(adapterEventliste);

        adapterEventliste.setOnItemClickListener(new RecyclerAdapterEvents.OnItemClickListener(){
            @Override
            public void onDeleteClick(int position){
                removeItem(position);
            }

            public void onItemClick(int position){
                changeItem(position);
            }
        });




    }

    private void readData(Kalender.MyCallback myCallback){
        if(eventlist.isEmpty()){
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    eventlist.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String event = dataSnapshot.child("bezeichnung").getValue(String.class);
                        String datum = dataSnapshot.child("datum").getValue(String.class);
                        String uhrzeit = dataSnapshot.child("uhrzeit").getValue(String.class);
                        String location = dataSnapshot.child("ort").getValue(String.class);
                        String hinweis = dataSnapshot.child("hinweise").getValue(String.class);
                        String id = dataSnapshot.getKey();
                        Event ev = new Event(event, datum, uhrzeit, location, hinweis, id);
                        eventlist.add(ev);
                    }

                    myCallback.onCallback(eventlist);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("Tag", "loadPost:onCancelled", error.toException());
                }
            });

        }
    }

    public interface MyCallback{
        void onCallback(ArrayList<Event> ev);
    }

    public void removeItem(int position){
        Event eventDelete = eventlist.get(position);
        eventlist.remove(position);
        adapterEventliste.notifyItemRemoved(position);
        DatabaseReference evdel = FirebaseDatabase.getInstance().getReference("PK/Event").child(eventDelete.getId());
        evdel.removeValue();
    }


    public void changeItem(int position) {
        adapterEventliste.notifyItemChanged(position);
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public String getMonth(int month){
        switch (month){
            case 0:
                return "Januar";
            case 1:
                return "Februar";
            case 2:
                return "März";
            case 3:
                return "April";
            case 4:
                return "Mai";
            case 5:
                return "Juni";
            case 6:
                return "Juli";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "Oktober";
            case 10:
                return "November";
            case 11:
                return "Dezember";
        }

        return "Fehler Datum";
    }

}