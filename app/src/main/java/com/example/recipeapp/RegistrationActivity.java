package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    EditText editText_register_email,editText_register_password;
    Button button_register;
    ProgressBar id_register_progressBar;
    FirebaseAuth mAuth;
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        mAuth = FirebaseAuth.getInstance();
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_register_progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email=editText_register_email.getText().toString();
                password=editText_register_password.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(RegistrationActivity.this,"Please enter an email",Toast.LENGTH_LONG).show();
                }
                if(password.isEmpty()){
                    Toast.makeText(RegistrationActivity.this,"Please enter a password",Toast.LENGTH_LONG).show();
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                id_register_progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this, "Account created",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    void init(){
        editText_register_email=findViewById(R.id.editText_register_email);
        editText_register_password=findViewById(R.id.editText_register_password);
        button_register=findViewById(R.id.button_register);
        id_register_progressBar=findViewById(R.id.id_register_progressBar);

    }
}