package com.example.miniprojectex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    UserControllerRESTAPI restapi;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadUsers(View view) {
        restapi = new UserControllerRESTAPI();
        restapi.start();
    }

    public void displayUsers(View view) {
        TextView tv = findViewById(R.id.tv);
        if(restapi !=null){
            users = restapi.getUsers();
            if(users != null){
                tv.setText("users count :"+users.data.size() +"\n"+
                        users.data.toString());
                tv.setMovementMethod(new ScrollingMovementMethod());

            }
            else {
                tv.setText("Empty List");
            }
        }else{
            tv.setText("Users not loaded ");
        }
    }
}