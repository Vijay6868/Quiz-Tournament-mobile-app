package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.quizAndUsers.UserManager;
import com.google.firebase.auth.FirebaseAuth;


public class F_Profile extends Fragment {

    public F_Profile() {
        // Required empty public constructor
    }

//======================================================================
    TextView logout, tv_email;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__profile, container, false);
         logout = view.findViewById(R.id.lb_logOut);
         handleLogout();
        UserManager userManager = UserManager.getInstance();
        tv_email = view.findViewById(R.id.lb_email);
        tv_email.setText(userManager.getEmail());


    return view;
    }

    private void handleLogout() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}