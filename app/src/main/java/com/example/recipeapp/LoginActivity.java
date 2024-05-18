package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText editText_email,editText_password;
    Button button_sign_in;
    TextView from_signin_to_registration;
    FirebaseAuth mAuth;
    ProgressBar id_login_progressBar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_login_progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email=editText_email.getText().toString();
                password=editText_password.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter an email",Toast.LENGTH_LONG).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter a password",Toast.LENGTH_LONG).show();

                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                id_login_progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LoginActivity.this, "Login successful.",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
        from_signin_to_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
    void init(){
        editText_email=findViewById(R.id.editText_email);
        editText_password=findViewById(R.id.editText_password);
        button_sign_in=findViewById(R.id.button_sign_in);
        from_signin_to_registration=findViewById(R.id.from_signin_to_registration);
        mAuth = FirebaseAuth.getInstance();
        id_login_progressBar=findViewById(R.id.id_login_progressBar);

    }
}