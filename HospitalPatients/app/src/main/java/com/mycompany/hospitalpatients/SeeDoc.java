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
import com.mycompany.hospitalpatients.Models.Staff;

public class SeeDoc extends AppCompatActivity {

    RecyclerView recviewDoc;
    MyAdapterDoctor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_doc);

        recviewDoc = findViewById(R.id.recviewDoc);
        recviewDoc.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Staff> options =
                new FirebaseRecyclerOptions.Builder<Staff>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Staff"), Staff.class)
                        .build();

        adapter = new MyAdapterDoctor(options);
        recviewDoc.setAdapter(adapter);

        Button toIndex = (Button) findViewById(R.id.toIndex);
        toIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null){
                    startActivity(new Intent(SeeDoc.this, Guest.class));
                }
                else {
                    startActivity(new Intent(SeeDoc.this, UserMain.class));
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