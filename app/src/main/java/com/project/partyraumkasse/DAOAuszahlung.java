package com.project.partyraumkasse;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOAuszahlung {
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public DAOAuszahlung() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("PK/" + Auszahlung.class.getSimpleName());
    }

    public Task<Void> add(Auszahlung az){
            return databaseReference.push().setValue(az);
    }
}
