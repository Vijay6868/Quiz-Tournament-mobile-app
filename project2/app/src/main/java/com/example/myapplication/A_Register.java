package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class A_Register extends AppCompatActivity {
    Button btRegister;
    EditText email, password, cpassword;
    TextView w_uname, w_password, w_cpassword;
    private FirebaseAuth auth;
    String _email, _password, _cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.tbUsername);
        password = findViewById(R.id.tbPassword);
        cpassword = findViewById(R.id.tbConfirmPassword);

        btRegister = findViewById(R.id.btRegister);

        w_cpassword = findViewById(R.id.wlb_confirm_password);
        w_cpassword = findViewById(R.id.wlb_password);
        w_uname = findViewById(R.id.wlb_uname);

        auth = FirebaseAuth.getInstance();

        handBtRegister();

    }

    private void handBtRegister() {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 _email = email.getText().toString();
                 _password = password.getText().toString();
                 _cpassword = cpassword.getText().toString();


                if(TextUtils.isEmpty(_email)){
                    w_uname.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(_password) && (_password.length()<4)) {
                    w_uname.setVisibility(View.VISIBLE);
                }
                else if(TextUtils.isEmpty(_cpassword) || !_password.equals(_cpassword)){
                    w_cpassword.setVisibility(View.VISIBLE);
                }
                else{
                    registerPlayer();
                }
            }
        });
    }

    private void registerPlayer() {
        auth.createUserWithEmailAndPassword(_email, _password).addOnCompleteListener(A_Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(A_Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(A_Register.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}