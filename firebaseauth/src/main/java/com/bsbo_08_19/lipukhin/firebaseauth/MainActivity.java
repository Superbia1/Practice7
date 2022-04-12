package com.bsbo_08_19.lipukhin.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText loginEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logIn = findViewById(R.id.buttonLogIn);
        Button signIn = findViewById(R.id.buttonSignIn);
        loginEditText = findViewById(R.id.editTextLogin);
        passwordEditText = findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        logIn.setOnClickListener(view -> {
            if (!(loginEditText.getText().toString().isEmpty()
                    || passwordEditText.getText().toString().isEmpty())) {
                mAuth.signInWithEmailAndPassword(loginEditText.getText().toString(),
                        passwordEditText.getText().toString()).addOnSuccessListener(task -> {
                    startActivity(new Intent(this, SecondActivity.class));
                    finish();
                }).addOnFailureListener(e ->
                        Toast.makeText(MainActivity.this,
                        e.getMessage(),
                        Toast.LENGTH_SHORT)
                        .show());
            } else {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        signIn.setOnClickListener(view -> {
            if (!(loginEditText.getText().toString().isEmpty()
                    || passwordEditText.getText().toString().isEmpty())) {
                mAuth.createUserWithEmailAndPassword(loginEditText.getText().toString(),
                        passwordEditText.getText().toString())
                        .addOnSuccessListener(task -> {
                            startActivity(new Intent(this, SecondActivity.class));
                            finish();
                        }).addOnFailureListener(e ->
                        Toast.makeText(MainActivity.this,
                                e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show());
            } else {
                Toast.makeText(this, "Поля пустые", Toast.LENGTH_SHORT)
                        .show();
            }

        });
    }
}