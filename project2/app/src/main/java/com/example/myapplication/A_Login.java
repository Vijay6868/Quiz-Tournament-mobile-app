package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class A_Login extends AppCompatActivity {
    EditText username, password;
    Button btlogin;
    String _username, _password,_userType;
    TextView w_username, w_password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        _userType = getIntent().getStringExtra("userType");

        loginInput();
        loginAlert();
        handleLoginButton();
    }
    public void loginInput(){
        username = findViewById(R.id.tbUsername_login);
        password = findViewById(R.id.tbPassword_login);

    }
    public void loginAlert(){
        w_username = findViewById(R.id.wlb_uname);
        w_password = findViewById(R.id.wlb_password);
    }
    public void handleLoginButton(){
        btlogin = findViewById(R.id.btLogin);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _username = username.getText().toString();
                _password = password.getText().toString();
                boolean check = validateInputs();
                if(check){
                    auth.signInWithEmailAndPassword(_username,_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent intent = new Intent(A_Login.this, A_Home.class);
                            startActivity(intent);
                        }
                    });
                }

            }
        });
    }
    public boolean validateInputs() {

        Validator validator = new Validator();
        boolean isValid = true;

        if (!validator.isValidEmail(_username)) {
            w_username.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            w_username.setVisibility(View.GONE);
        }
        if (!validator.isValidPassword(_password)) {
            w_password.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            w_password.setVisibility(View.GONE);
        }


        return isValid;
    }
}