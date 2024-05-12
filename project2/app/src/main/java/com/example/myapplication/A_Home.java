package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class A_Home extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView navBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<String> options = new ArrayList<>();
        options.add("option1");
        options.add("option1");
        options.add("option1");

        FirebaseDatabase.getInstance().getReference().child("Options").setValue(options);

        frameLayout = findViewById(R.id.main_frame);
        navBar = findViewById(R.id.nav_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Quizzes()).commit();
        navBar.setSelectedItemId(R.id.nav_quizzes);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if(itemId == R.id.nav_quizzes){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Quizzes()).commit();
                }
                else if(itemId == R.id.nav_profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Profile()).commit();
                }
                else if (itemId == R.id.nav_completed) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Create_Quiz()).commit();
                }

                return true;
            }
        });
    }
}