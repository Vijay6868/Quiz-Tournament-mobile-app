package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.quizAndUsers.Quiz;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_QuizField#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F_QuizField extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Quiz quiz;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F_QuizField() {
        // Required empty public constructor
    }
    public F_QuizField(Quiz quiz){
        this.quiz = quiz;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_QuizField.
     */
    // TODO: Rename and change types and number of parameters
    public static F_QuizField newInstance(String param1, String param2) {
        F_QuizField fragment = new F_QuizField();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__quiz_field, container, false);
        return view;
    }
}