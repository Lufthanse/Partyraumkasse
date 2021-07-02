package com.project.partyraumkasse;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOEinkauf {
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public DAOEinkauf() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Einkauf.class.getSimpleName());
    }

    public Task<Void> add(Einkauf ek){
        return databaseReference.push().setValue(ek);
    }
}
