package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.quizAndUsers.UserManager;
import com.example.myapplication.quizAndUsers.UserTypeCallback;
import com.google.firebase.auth.FirebaseAuth;


public class F_Profile extends Fragment {

    public F_Profile() {
        // Required empty public constructor
    }

//======================================================================
    TextView logout, tv_email, tv_userType;
    View view;
    ImageView img_userType;
    UserManager userManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__profile, container, false);
         logout = view.findViewById(R.id.lb_logOut);
         handleLogout();
        userManager = UserManager.getInstance();
        tv_email = view.findViewById(R.id.lb_email);
        tv_email.setText(userManager.getEmail());
        img_userType = view.findViewById(R.id.ic_user_type);
        tv_userType = view.findViewById(R.id.lb_userType);

        handleUserTypeIc();

    return view;
    }

    private void handleUserTypeIc() {
      userManager.getUserType(new UserTypeCallback() {
          @Override
          public void onCallback(String userType) {
              if(userType.equals("Admins")){
                  img_userType.setImageResource(R.drawable.ic_admin);
                  tv_userType.setText("Admin");

              }
          }
      });
    }

    private void handleLogout() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
               clearBackStack();
               UserManager.resetInstance();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                UserManager.resetInstance();
                getActivity().finish();
            }
        });
    }
    public void clearBackStack() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}