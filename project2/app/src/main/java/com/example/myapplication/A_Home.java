package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myapplication.quizAndUsers.UserManager;
import com.example.myapplication.quizAndUsers.UserTypeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class A_Home extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView navBar;
    UserManager userManager;
   LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadDialog();
        frameLayout = findViewById(R.id.main_frame);
        navBar = findViewById(R.id.nav_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Quizzes()).commit();
        navBar.setSelectedItemId(R.id.nav_quizzes);


        userManager = UserManager.getInstance();
        handleBottomNav();
    }

    private void loadDialog() {
        loadingDialog = new LoadingDialog(A_Home.this);
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               loadingDialog.dismissDialog();
            }
        },1000);
    }


    public void handleBottomNav(){
        userManager.getUserType(new UserTypeCallback() {
            @Override
            public void onCallback(String userType) {
                MenuItem item = navBar.getMenu().findItem(R.id.nav_completed);
                if(userType.equals("Admins")){
                    item.setIcon(R.drawable.ic_add);
                    item.setTitle("Add Quiz");
                }

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
                            if(userType.equals("Admins")){
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Create_Quiz()).commit();

                            }
                            else{
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_Leaderboard()).commit();
                                //item.setIcon(R.drawable.ic_add);
                            }
                        }

                        return true;
                    }
                });


            }
        });
    }
}