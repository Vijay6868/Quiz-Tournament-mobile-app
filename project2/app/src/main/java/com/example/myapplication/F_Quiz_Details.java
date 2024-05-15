package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.quizAndUsers.Quiz;
import com.example.myapplication.quizAndUsers.QuizManager;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Quiz_Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F_Quiz_Details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F_Quiz_Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Quiz_Details.
     */
    // TODO: Rename and change types and number of parameters
    public static F_Quiz_Details newInstance(String param1, String param2) {
        F_Quiz_Details fragment = new F_Quiz_Details();
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
    //================================================================================
    View view;
    EditText qname;
    TextView difficulty, category, noOfQues, w_sdate, w_edate, sdate,edate,w_title;
    Button btClose, btStart, btUpdate;
    String _start_date, _end_date,_qname;
    Validator validator;
    Quiz quiz;
    QuizManager quizManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f_quiz_details, container, false);


         difficulty = view.findViewById(R.id.spin_difficulty);
         category = view.findViewById(R.id.spin_category);
         noOfQues = view.findViewById(R.id.spin_ques_type);

         validator = new Validator();
         w_sdate = view.findViewById(R.id.wlb_start_date);
         w_edate = view.findViewById(R.id.wlb_end_date);
         w_title = view.findViewById(R.id.wlb_quiz_name);
         sdate = view.findViewById(R.id.start_date);
         edate = view.findViewById(R.id.end_date);
         qname = view.findViewById(R.id.tbTitle);
         setQuizDetails();
         inputs();

         handleBtClose();
         handleDateSelection();
         handleBtUpdate();

        return view;
    }
    public void inputs(){

        _qname = qname.getText().toString();

        _start_date = sdate.getText().toString();

        _end_date = edate.getText().toString();
    }

    private void handleBtUpdate() {

        btUpdate = view.findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputs();
                boolean check = validateInputs();

                if(check){
                    quiz.setEdate(_end_date);
                    quiz.setSdate((_start_date));
                    quiz.setQname(_qname);

                    quizManager = new QuizManager(quiz);
                    quizManager.updateQuizData();
                }

            }
        });


    }

    private void handleBtClose() {
        btClose = view.findViewById(R.id.btClose);
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                F_Quizzes fQuizzes = new F_Quizzes();

                // Replace the current fragment with the deal details fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, fQuizzes)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void setQuizDetails(){
        Bundle bundle = getArguments();

        if(bundle !=null){
             quiz = (Quiz) bundle.getSerializable("quiz");
            // use quiz object to populate the quiz_details fields
            if(quiz != null){
                qname.setText(quiz.getQname());
                difficulty.setText(quiz.getDifficulty());
                category.setText(quiz.getCategory());
                noOfQues.setText(quiz.getNoOfQues());
                sdate.setText(quiz.getSdate());
                edate.setText(quiz.getEdate());
            }

        }
    }
    public void handleDateSelection() {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        // Start date selection

        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputs();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        sdate.setText(selectedDate);
                        boolean validDate = validator.isDateSmallerThenOriginal(selectedDate,quiz.getSdate());
                        //boolean validDate = validator.isValidDate(selectedDate);

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

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputs();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        edate.setText(selectedDate);

                        boolean validDate = validator.isDateSmallerThenOriginal(selectedDate,_start_date);

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
    public boolean validateInputs(){
        Validator validator = new Validator();
        boolean isValid = true;


        if(!validator.isValidName(_qname)){

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
//            if(validator.isValidDate(_start_date)){
//                w_sdate.setVisibility(view.GONE);
//            }else {
//                w_sdate.setVisibility(view.VISIBLE);
//                isValid = false;
//            }
            if(validator.isDateSmallerThenOriginal(_start_date,quiz.getSdate())){
                w_edate.setVisibility(view.GONE);
            }
            else {
                w_edate.setVisibility(view.VISIBLE);
                isValid = false;
            }

        }
        if (_end_date.isEmpty()) {
            w_edate.setVisibility(view.VISIBLE);
            isValid =false;
        } else {
//            if(validator.isValidDate(_end_date)){
//                w_edate.setVisibility(view.GONE);
//            }else {
//                w_edate.setVisibility(view.VISIBLE);
//                isValid = false;
//            }
            if(validator.isDateSmallerThenOriginal(_end_date,_start_date)){
                w_edate.setVisibility(view.GONE);
            }
            else {
                w_edate.setVisibility(view.VISIBLE);
                isValid = false;
            }

        }

        return isValid;
    }
}