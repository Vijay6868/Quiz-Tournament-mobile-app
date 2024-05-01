package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class A_SelectRegistrationRole extends AppCompatActivity {
    Button btPlayer, btAdmin;
    TextView tvClickHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration_role);
        btAdmin = findViewById(R.id.btAdmin);
        //tvClickHere = findViewById(R.id.tv_already_have_account_click_here);
        btPlayer = findViewById(R.id.btPlayer);
        //clickHere();
        btAdmin();
        btPlayer();

    }
    public void btPlayer(){

        btPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the Login activity and pass user type as an extra
                Intent intent = new Intent(A_SelectRegistrationRole.this, A_Register.class);
                intent.putExtra("userType", "player");
                startActivity(intent);
            }
        });

    }
    public void btAdmin(){
        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the Login activity and pass user type as an extra
                Intent intent = new Intent(A_SelectRegistrationRole.this, A_Register.class);
                intent.putExtra("userType", "admin");
                startActivity(intent);
            }
        });
    }
}