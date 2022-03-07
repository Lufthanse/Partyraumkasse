package com.project.partyraumkasse;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOEinzahlung {
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public DAOEinzahlung() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("PK/" + Einzahlung.class.getSimpleName());
    }

    public Task<Void> add(Einzahlung ez){
            return databaseReference.push().setValue(ez);
    }
}
