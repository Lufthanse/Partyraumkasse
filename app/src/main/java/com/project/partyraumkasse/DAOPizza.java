package com.project.partyraumkasse;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPizza {
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public DAOPizza() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("PK/" + Pizza.class.getSimpleName());
    }

    public Task<Void> add(Pizza pz){
            return databaseReference.push().setValue(pz);
    }
}
