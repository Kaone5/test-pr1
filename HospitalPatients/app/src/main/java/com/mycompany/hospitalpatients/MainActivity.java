package com.mycompany.hospitalpatients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button buttonGuest, buttonReg, buttonEnter;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout main_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGuest = findViewById(R.id.buttonGuest);
        buttonReg = findViewById(R.id.buttonReg);
        buttonEnter = findViewById(R.id.buttonEnter);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        main_enter = findViewById(R.id.main_enter);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Reg.class);
                startActivity(intent);
            }
        });

        buttonGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Guest.class);
                startActivity(intent);
            }
        });

        final EditText enterLogin = findViewById(R.id.enterLogin);
        final EditText enterPassword = findViewById(R.id.enterPassword);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(enterLogin.getText().toString())) {
                    Snackbar.make(main_enter, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(enterPassword.getText().toString())) {
                    Snackbar.make(main_enter, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else {
                    auth.signInWithEmailAndPassword(enterLogin.getText().toString(), enterPassword.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(MainActivity.this, UserMain.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(main_enter, "Ошибка авторизации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
