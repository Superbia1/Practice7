package com.bsbo_08_19.lipukhin.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        Button logOut = findViewById(R.id.buttonLogOut);
        logOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText("Ваш ID: "+ mAuth.getCurrentUser().getUid()
                + "\nВаша почта: " + mAuth.getCurrentUser().getEmail());
    }
}