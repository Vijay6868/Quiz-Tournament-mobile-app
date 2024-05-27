package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class F_Leaderboard extends Fragment {
    View view;
    public F_Leaderboard() {
        // Required empty public constructor
    }

    // for future development of the app
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__leaderboard, container, false);
         return view;
    }
}