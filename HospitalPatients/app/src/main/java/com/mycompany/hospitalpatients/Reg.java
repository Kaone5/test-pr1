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
import com.mycompany.hospitalpatients.Models.User;

public class Reg extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        root = findViewById(R.id.root_element);

        Button toMain = (Button) findViewById(R.id.buttonLogOut);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reg.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final EditText textLogin = findViewById(R.id.textLogin);
        final EditText textPassword = findViewById(R.id.textPassword);
        final EditText textRepeatPassword = findViewById(R.id.textRepeatPassword);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(textLogin.getText().toString())) {
                    Snackbar.make(root, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(textPassword.getText().toString())) {
                    Snackbar.make(root, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (textPassword.getText().toString().length() < 6) {
                    Snackbar.make(root, "Пароль должен быть больше 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (!(textRepeatPassword.getText().toString().equals(textPassword.getText().toString())))  {
                    Snackbar.make(root, "Пароли не совпадают", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(textLogin.getText().toString(), textPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setLogin(textLogin.getText().toString());
                        user.setPass(textPassword.getText().toString());

                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(root, "Вы зарегистрированы", Snackbar.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Reg.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Пользователь существует. " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}