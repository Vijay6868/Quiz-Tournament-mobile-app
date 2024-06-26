package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button btPlayer, btAdmin;
    TextView tvClickHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAdmin = findViewById(R.id.btAdmin);
        tvClickHere = findViewById(R.id.tv_already_have_account_click_here);
        btPlayer = findViewById(R.id.btPlayer);
        clickHere();
        btCreateAccount();
        btPlayer();

    }
    //move to registration actvity, user selects register as Admin or Player
    public void clickHere(){
        tvClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, A_SelectRegistrationRole.class);
                startActivity(intent);
            }
        });
    }
    public void btPlayer(){

        btPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the Login activity and pass user type as an extra
                Intent intent = new Intent(MainActivity.this, A_Login.class);
                intent.putExtra("userType", "Players");
                startActivity(intent);
            }
        });

    }
    public void btCreateAccount(){
        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the Login activity and pass user type as an extra
                Intent intent = new Intent(MainActivity.this, A_SelectRegistrationRole.class);
                //intent.putExtra("userType", "Admins");
                startActivity(intent);
            }
        });
    }

}