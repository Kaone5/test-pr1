package com.mycompany.hospitalpatients;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Button buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button seeDoc = (Button) findViewById(R.id.seeDoc);
        seeDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest.this, SeeDoc.class);
                startActivity(intent);
            }
        });

        Button seeHos = (Button) findViewById(R.id.seeHos);
        seeHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest.this, SeeHos.class);
                startActivity(intent);
            }
        });
    }

}