package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication.api.DataCallback;
import com.example.myapplication.api.QuestionModelControllerAPI;
import com.example.myapplication.api.QuestionsModelList;
import com.example.myapplication.quizAndUsers.Quiz;
import com.example.myapplication.quizAndUsers.QuizManager;

import java.util.ArrayList;
import java.util.Calendar;

public class F_Create_Quiz extends Fragment implements DataCallback {


    public F_Create_Quiz() {
        // Required empty public constructor
    }

    //===========================
    Spinner sp_difficulty, sp_type, sp_category, sp_no_of_ques;
    TextView start_date, end_date;
    EditText name;
    String _start_date, _end_date, _name, _difficulty, _type, _category, _no_of_ques;
    View view;
    TextView w_title, w_sdate, w_edate;
    Validator validator;
    Button btCreate;
    QuestionModelControllerAPI questionModelControllerAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.f__create__quiz, container, false);

        inputs();
        handleSpDifficulty();
        handleSpType();
        handleSpCategory();
        handleSpNoOfQues();
        handleDateSelection();
        wLabels();
        handleBtCreate();

        validator = new Validator();
        return view;
    }



    public void inputs(){
        name = view.findViewById(R.id.tbTitle);
        _name = name.getText().toString();
        start_date = view.findViewById(R.id.start_date);
        _start_date = start_date.getText().toString();
        end_date = view.findViewById(R.id.end_date);
        _end_date = end_date.getText().toString();
    }

    public void handleBtCreate() {
        btCreate = view.findViewById(R.id.btCreate);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputs();
                boolean check = validateInputs();
                if(check){
                    Toast.makeText(getContext(), "quiz created", Toast.LENGTH_SHORT).show();
                    apiCall();
                    displayCreateQuizFrag();
                }
            }
        });
    }

    // dropdown category implementation
    public void handleSpCategory(){
        sp_difficulty = view.findViewById(R.id.spin_difficulty);
        sp_difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _category= parent.getItemAtPosition(position).toString();
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
    // dropdown quiz type implementation
    public void handleSpType(){
        sp_type = view.findViewById(R.id.spin_ques_type);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _type = parent.getItemAtPosition(position).toString();
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
    // dropdown quiz difficulty implementation
    public void handleSpDifficulty(){
        sp_category = view.findViewById(R.id.spin_category);
        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _difficulty= parent.getItemAtPosition(position).toString();
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
    // dropdown quiz no of ques implementation
    public void handleSpNoOfQues(){
        sp_no_of_ques = view.findViewById(R.id.no_of_ques);
        sp_no_of_ques.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _no_of_ques = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        // populate dropdown list
        for(int i=1;i<=10;i++){
            String _i = Integer.toString(i);
            options.add(_i);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_2, options);
        sp_no_of_ques.setAdapter(adapter);
    }
    // quiz date selection dialog
    public void handleDateSelection() {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        // Start date selection

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        start_date.setText(selectedDate);
                        boolean validDate = validator.isValidDate(selectedDate);
                        
                        if(validDate){
                            w_sdate.setVisibility(view.INVISIBLE);
                            //Toast.makeText(getContext(), "valid", Toast.LENGTH_SHORT).show();
                            _start_date = selectedDate;
                        }
                        else {
                            w_sdate.setVisibility(view.VISIBLE);
                        }
                    
                    }
                }, currentYear, currentMonth, currentDay);
                datePickerDialog.show();
            }
        });

        // End date selection

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        end_date.setText(selectedDate);

                        boolean validDate = validator.isValidDate(selectedDate);

                        if(validDate){
                            w_edate.setVisibility(view.INVISIBLE);
                            //Toast.makeText(getContext(), "valid", Toast.LENGTH_SHORT).show();
                            _end_date = selectedDate;
                        }
                        else {
                            w_edate.setVisibility(view.VISIBLE);
                        }

                    }
                }, currentYear, currentMonth, currentDay);
                datePickerDialog.show();
            }
        });
    }
    //validate quiz data, called upon create button
    public boolean validateInputs(){
        Validator validator = new Validator();
        boolean isValid = true;


        if(!validator.isValidName(_name)){

            w_title.setVisibility(view.VISIBLE);
            isValid = false;
        }
        else {
            w_title.setVisibility(view.GONE);
        }
        if (_start_date.isEmpty() ) {
            w_sdate.setVisibility(view.VISIBLE);
            isValid =false;
        } else {
            if(validator.isValidDate(_start_date)){
                w_sdate.setVisibility(view.GONE);
            }else {
                w_sdate.setVisibility(view.VISIBLE);
                isValid = false;
            }

        }
        if (_end_date.isEmpty()) {
            w_edate.setVisibility(view.VISIBLE);
            isValid =false;
        } else {
            if(validator.isValidDate(_end_date)){
                w_edate.setVisibility(view.GONE);
            }else {
                w_edate.setVisibility(view.VISIBLE);
                isValid = false;
            }
        }
        return isValid;
    }

    @Override
    public void onDataLoaded(QuestionsModelList list) {

        Quiz quiz = new Quiz(_name,_start_date,_end_date,_type,_difficulty,_category,_no_of_ques,list);
        QuizManager quizManager = new QuizManager(quiz);
        quizManager.insertQuizData();
    }
    public void wLabels(){
        w_title = view.findViewById(R.id.wlb_quiz_name);
        w_sdate = view.findViewById(R.id.wlb_start_date);
        w_edate = view.findViewById(R.id.wlb_end_date);
    }
    private void apiCall() {
        questionModelControllerAPI = new QuestionModelControllerAPI(getContext(),this,
                _type,_category,_difficulty,_no_of_ques);
        questionModelControllerAPI.getData();

    }
    public void displayCreateQuizFrag(){
        F_Create_Quiz fCreateQuiz = new F_Create_Quiz();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame,fCreateQuiz).addToBackStack(null)
                .commit();
    }
}