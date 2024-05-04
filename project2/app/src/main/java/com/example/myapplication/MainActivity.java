package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.api.QuestionControllerRESTAPI;


public class MainActivity extends AppCompatActivity {
    Button btPlayer, btAdmin;
    TextView tvClickHere;
    QuestionControllerRESTAPI questionControllerRESTAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAdmin = findViewById(R.id.btAdmin);
        tvClickHere = findViewById(R.id.tv_already_have_account_click_here);
        btPlayer = findViewById(R.id.btPlayer);
        clickHere();
        btAdmin();
        btPlayer();

    }
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
                Intent intent = new Intent(MainActivity.this, A_Login.class);
                intent.putExtra("userType", "admin");
                startActivity(intent);
            }
        });
    }
    public void loaddata(View view){
        questionControllerRESTAPI = new QuestionControllerRESTAPI();
        questionControllerRESTAPI.start();
    }
}