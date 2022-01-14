package com.project.partyraumkasse;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOEvent {
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public DAOEvent() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Event.class.getSimpleName());
    }

    public Task<Void> add(Event ev){
            return databaseReference.push().setValue(ev);
    }
}
