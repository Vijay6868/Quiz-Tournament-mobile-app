package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.api.DataCallback;
import com.example.myapplication.api.QuestionsModelList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Create_Quiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F_Create_Quiz extends Fragment implements DataCallback {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F_Create_Quiz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Create_Quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static F_Create_Quiz newInstance(String param1, String param2) {
        F_Create_Quiz fragment = new F_Create_Quiz();
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
    //===========================
    Spinner sp_difficulty, sp_question_type, sp_category;
    TextView start_date, end_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__create__quiz, container, false);
        sp_difficulty = view.findViewById(R.id.spin_difficulty);

        handleSpDifficulty();
        //handleDateSelection();
        return view;
    }
    public void handleSpDifficulty(){
        sp_difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("hard");
        options.add("moderate");
        options.add("easy");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_2, options);
        //adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        sp_difficulty.setAdapter(adapter);
    }
    public void handleDateSelection(){
        start_date = (TextView) getView().findViewById(R.id.start_date);
        end_date = (TextView) getView().findViewById(R.id.start_date);
    }

    @Override
    public void onDataLoaded(QuestionsModelList list) {
        
    }
}