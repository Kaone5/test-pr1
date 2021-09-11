package com.mycompany.hospitalpatients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mycompany.hospitalpatients.Models.Hospital;

public class SeeHos extends AppCompatActivity {
    RecyclerView recviewHos;
    MyAdapterHospital adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_hos);

        recviewHos = findViewById(R.id.recviewHos);
        recviewHos.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Hospital> options =
                new FirebaseRecyclerOptions.Builder<Hospital>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hospital"), Hospital.class)
                        .build();

        adapter = new MyAdapterHospital(options);
        recviewHos.setAdapter(adapter);

        Button toIndex = (Button) findViewById(R.id.toIndex);
        toIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null){
                    startActivity(new Intent(SeeHos.this, Guest.class));
                }
                else {
                    startActivity(new Intent(SeeHos.this, UserMain.class));
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}