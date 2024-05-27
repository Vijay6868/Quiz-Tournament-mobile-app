package com.example.myapplication;

import static java.lang.reflect.Array.getInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class F_Display_Score extends Fragment {



    public F_Display_Score() {
        // Required empty public constructor
    }
    Button btBackToQuizzes;
    TextView tv_score;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.f__display__score, container, false);
        Bundle bundle = getArguments();
        tv_score = view.findViewById(R.id.tv_final_score);
        if(bundle!=null){
            int score = bundle.getInt("final_score");
            String _score = String.valueOf(score);
            tv_score.setText("Final Score: "+_score);
        }


        btBackToQuizzes = view.findViewById(R.id.bt_retHome);
        handlebtBackToQuizzes();
        return view;
    }

    private void handlebtBackToQuizzes() {
        btBackToQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                F_Quizzes fQuizzes = new F_Quizzes();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, fQuizzes)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}