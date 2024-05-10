package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.api.DataCallback;
import com.example.myapplication.api.QuestionsModelList;

import java.util.ArrayList;
import java.util.Calendar;

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
    Spinner sp_difficulty, sp_type, sp_category, sp_no_of_ques;
    TextView start_date, end_date;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__create__quiz, container, false);


        handleSpDifficulty();
        handleSpType();
        handleSpCategory();
        handleSpNoOfQues();
        handleDateSelection();
        return view;
    }



    public void handleSpDifficulty(){
        sp_difficulty = view.findViewById(R.id.spin_difficulty);
        sp_difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("general Knowledge"); // 9
        options.add("books"); // 10
        options.add("films"); // 11
        options.add("music"); // 12
        options.add("television"); // 14
        options.add("video games"); // 15
        options.add("computer science"); // 18

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_2, options);
        sp_difficulty.setAdapter(adapter);
    }
    public void handleSpType(){
        sp_type = view.findViewById(R.id.spin_ques_type);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("true or false"); // boolean
        options.add("multiple");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_2, options);
        sp_type.setAdapter(adapter);
    }
    public void handleSpCategory(){
        sp_category = view.findViewById(R.id.spin_category);
        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("easy");
        options.add("medium");
        options.add("hard");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_2, options);
        sp_category.setAdapter(adapter);
    }
    public void handleSpNoOfQues(){
        sp_no_of_ques = view.findViewById(R.id.no_of_ques);
        sp_no_of_ques.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("3");
        options.add("4");
        options.add("5");
        options.add("6");
        options.add("7");
        options.add("8");
        options.add("9");
        options.add("10");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_2, options);
        sp_no_of_ques.setAdapter(adapter);
    }

    public void handleDateSelection(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        start_date = view.findViewById(R.id.start_date);
        end_date = view.findViewById(R.id.end_date);

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        start_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onDataLoaded(QuestionsModelList list) {
        
    }
}