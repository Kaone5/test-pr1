package com.mycompany.hospitalpatients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DelPatient extends AppCompatActivity {
    private EditText delCard;
    private Button buttonDel;
    private ConstraintLayout del_patient_element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_patient);

        del_patient_element = findViewById(R.id.del_patient_element);
        init();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Button buttonDel = (Button) findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardnumber = delCard.getText().toString();
                if (TextUtils.isEmpty(delCard.getText().toString())) {
                    Snackbar.make(del_patient_element, "Введите номер карты", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else {
                    ref.child("Patients").orderByChild("cardnumber").equalTo(cardnumber).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        child.getRef().setValue(null);
                                    }
                                    Snackbar.make(findViewById(android.R.id.content), "Пациент удален", Snackbar.LENGTH_SHORT).show();
                                    Intent intent=new Intent(DelPatient.this, UserMain.class);
                                    startActivity(intent);
                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.w("TodoApp", "getUser:onCancelled", databaseError.toException());
                                }
                            });
                }
            }
        });
        Button toIndex = (Button) findViewById(R.id.toIndex);
        toIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DelPatient.this, UserMain.class));
            }
        });

    }
    private void init(){
        delCard = findViewById(R.id.delCard);
        buttonDel = findViewById(R.id.buttonDel);
    }
}