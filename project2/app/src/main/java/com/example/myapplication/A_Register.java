package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class A_Register extends AppCompatActivity {
    Button btRegister;
    EditText email, password, cpassword;
    TextView w_uname, w_password, w_cpassword;
    private FirebaseAuth auth;
    String _email, _password, _cpassword, uid, _userType;
    ArrayList<String> quiz_completed;
    ArrayList<String> quiz_created;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.tbUsername);
        password = findViewById(R.id.tbPassword);
        cpassword = findViewById(R.id.tbConfirmPassword);

        btRegister = findViewById(R.id.btRegister);

        w_cpassword = findViewById(R.id.wlb_confirm_password);
        w_password = findViewById(R.id.wlb_password);
        w_uname = findViewById(R.id.wlb_uname);

        auth = FirebaseAuth.getInstance();

        _userType = getIntent().getStringExtra("userType");

        handBtRegister();

    }
    // register user if details are correct
    private void handBtRegister() {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 _email = email.getText().toString();
                 _password = password.getText().toString();
                 _cpassword = cpassword.getText().toString();

                 boolean check = validateInputs();


               if(check){
                   registerPlayer();

               }
            }
        });
    }
    // register user attached and aatach their UID in in realtime database under
    // Player or Admin nodes
    private void registerPlayer() {
        auth.createUserWithEmailAndPassword(_email, _password).addOnCompleteListener(A_Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(A_Register.this, "User Registered", Toast.LENGTH_SHORT).show();


                    FirebaseUser user = auth.getCurrentUser();

                    if (user != null) {
                        // User is signed in
                        uid = user.getUid();

                        HashMap<String, Object> m= new HashMap<>();
                        if(_userType.equals("Admins")){
                            m.put("quizzes_created", quiz_created);
                        }
                        else{
                            m.put("quizzes_completed",quiz_completed);
                        }
                        m.put("user_type",_userType);

                        FirebaseDatabase.getInstance().getReference().child(_userType).child(uid).setValue(m);

                        Intent intent = new Intent(A_Register.this, MainActivity.class);
                        startActivity(intent);
                        //Log.d("UID", uid);
                    } else {
                        // No user is signed in
                        Log.d("UID", "No user signed in");
                    }


                }
                else {
                    Toast.makeText(A_Register.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //validate details are correct
    public boolean validateInputs(){

        Validator validator = new Validator();
        boolean isValid = true;

        if (!validator.isValidEmail(_email)) {
            w_uname.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            w_uname.setVisibility(View.GONE);
        }
        if (!validator.isValidPassword(_password)) {
            w_password.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            w_password.setVisibility(View.GONE);
        }
        // Validate confirm password.
        if(!validator.isValidMatch(_password,_cpassword)){
            w_cpassword.setVisibility(View.VISIBLE);
            isValid = false;
        } else{
            w_cpassword.setVisibility(View.GONE);
        }

        return isValid;
    }

}