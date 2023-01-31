package com.android.testapk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailLogin,passwordLogin;
    Button Login,Register;
    ProgressBar progressBarLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailLogin = findViewById(R.id.edtEmailLogin);
        passwordLogin = findViewById(R.id.edtPasswordLogin);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        Register = findViewById(R.id.btnRegister1);
        Register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
        Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(v -> {
            progressBarLogin.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString(),passwordLogin.getText().toString()).addOnCompleteListener(task -> {
                progressBarLogin.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

    }
}