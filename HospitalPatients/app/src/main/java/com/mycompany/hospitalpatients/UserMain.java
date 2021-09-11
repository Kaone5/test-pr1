package com.mycompany.hospitalpatients;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserMain extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference mDataBase;
    private String db = "Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        auth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(db);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(db);
        DatabaseReference current_userRef = userRef.child(uid);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String login = dataSnapshot.child("login").getValue(String.class);
                if (login.equals("admin@admin.com")){
                    setContentView(R.layout.activity_admin_main);

                    Button seeDoc = (Button) findViewById(R.id.seeDoc);
                    seeDoc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeeDoc.class);
                            startActivity(intent);
                        }
                    });

                    Button seeHos = (Button) findViewById(R.id.seeHos);
                    seeHos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeeHos.class);
                            startActivity(intent);
                        }
                    });

                    Button buttonEmergency = (Button) findViewById(R.id.seePatient);
                    buttonEmergency.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeePatient.class);
                            startActivity(intent);
                        }
                    });

                    Button addDoctor = (Button) findViewById(R.id.addDoctor);
                    addDoctor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, AddStaff.class);
                            startActivity(intent);
                        }
                    });

                    Button addHospital = (Button) findViewById(R.id.addHospital);
                    addHospital.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, AddHos.class);
                            startActivity(intent);
                        }
                    });

                    Button addPatient = (Button) findViewById(R.id.addPatient);
                    addPatient.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, AddPatient.class);
                            startActivity(intent);
                        }
                    });

                    Button delPatient = (Button) findViewById(R.id.delPatient);
                    delPatient.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, DelPatient.class);
                            startActivity(intent);
                        }
                    });

                    Button buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
                    buttonLogOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            auth.signOut();
                            startActivity(new Intent(UserMain.this, MainActivity.class));
                        }
                    });
                }
                else if (login.equals("doctor@doctor.com")){
                    setContentView((R.layout.activity_doctor_main));

                    Button seeDoc = (Button) findViewById(R.id.seeDoc);
                    seeDoc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeeDoc.class);
                            startActivity(intent);
                        }
                    });

                    Button seeHos = (Button) findViewById(R.id.seeHos);
                    seeHos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeeHos.class);
                            startActivity(intent);
                        }
                    });

                    Button buttonEmergency = (Button) findViewById(R.id.seePatient);
                    buttonEmergency.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeePatient.class);
                            startActivity(intent);
                        }
                    });

                    Button addPatient = (Button) findViewById(R.id.addPatient);
                    addPatient.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, AddPatient.class);
                            startActivity(intent);
                        }
                    });

                    Button buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
                    buttonLogOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            auth.signOut();
                            startActivity(new Intent(UserMain.this, MainActivity.class));
                        }
                    });
                }
                else {
                    setContentView(R.layout.activity_user_main);
                    Button seeDoc = (Button) findViewById(R.id.seeDoc);
                    seeDoc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeeDoc.class);
                            startActivity(intent);
                        }
                    });

                    Button seeHos = (Button) findViewById(R.id.seeHos);
                    seeHos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeeHos.class);
                            startActivity(intent);
                        }
                    });

                    Button buttonEmergency = (Button) findViewById(R.id.seePatient);
                    buttonEmergency.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserMain.this, SeePatient.class);
                            startActivity(intent);
                        }
                    });

                    Button buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
                    buttonLogOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            auth.signOut();
                            startActivity(new Intent(UserMain.this, MainActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        current_userRef.addListenerForSingleValueEvent(eventListener);

    }
}