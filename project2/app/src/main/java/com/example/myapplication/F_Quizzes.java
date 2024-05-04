package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Quizzes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F_Quizzes extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F_Quizzes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Quizzes.
     */
    // TODO: Rename and change types and number of parameters
    public static F_Quizzes newInstance(String param1, String param2) {
        F_Quizzes fragment = new F_Quizzes();
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
//============================================================================================
    Spinner sp_quiz_selection;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_f__quizzes, container, false);
        handleQuizSelectionSpin();
        return view;
    }
    public void handleQuizSelectionSpin(){
        sp_quiz_selection = view.findViewById(R.id.sp_quizzes);


        sp_quiz_selection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("ONGOING QUIZZES");
        options.add("COMING QUIZZES");
        options.add("PAST QUIZZES");
        options.add("ALL QUIZZES");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_layout, options);
        //adapter.setDropDownViewResource(R.layout.se);
        sp_quiz_selection.setAdapter(adapter);
    }

}