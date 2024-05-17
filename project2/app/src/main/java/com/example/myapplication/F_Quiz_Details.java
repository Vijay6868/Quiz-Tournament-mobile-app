package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication.quizAndUsers.Quiz;
import com.example.myapplication.quizAndUsers.QuizManager;

import java.util.Calendar;


public class F_Quiz_Details extends Fragment {


    private String mParam1;
    private String mParam2;

    public F_Quiz_Details() {
        // Required empty public constructor
    }

    //================================================================================
    View view;
    EditText qname;
    TextView difficulty, category, noOfQues, w_sdate, w_edate, sdate,edate,w_title,likes;
    Button btClose, btStart, btUpdate;
    String _start_date, _end_date,_qname,_likes;
    Validator validator;
    Quiz quiz;
    QuizManager quizManager;
    ImageView iv_delete;
    ToggleButton tgLike;
    int originalLikes;

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
         tgLike  = view.findViewById(R.id.tgLike);
         likes = view.findViewById(R.id.likes);
         setQuizDetails();
         inputs();
         handbtStart();
         handleDeleteQuiz();
         handleBtClose();
         handleDateSelection();
         handleBtUpdate();
         handleTgLike();

         originalLikes = quiz.getLikes();
        return view;
    }

    private void handbtStart() {
        btStart = view.findViewById(R.id.btStart);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuizFieldFrag();
            }
        });
    }

    private void handleTgLike() {

        tgLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(tgLike.isChecked()){
                    int newLike = quiz.getLikes()+1;
                    quiz.setLikes(newLike);
                    _likes = String.valueOf(newLike);
                    likes.setText(_likes);
                    //likes
                }
                else{
                    String _likes = String.valueOf(originalLikes);
                    likes.setText(_likes);
                    int newLike = quiz.getLikes()-1;
                    quiz.setLikes(newLike);
                }
            }
        });
    }
    private void handleDeleteQuiz() {
        iv_delete = view.findViewById(R.id.btDelete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inside your method where you want to delete the quiz
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to delete this quiz?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked Yes, delete the quiz
                        Toast.makeText(getContext(), "Quiz Deleted!!", Toast.LENGTH_SHORT).show();
                        quizManager = new QuizManager(quiz);
                        quizManager.deleteQuizData(); // Call your method to delete the quiz here
                        displayQuizzesFrag();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked No, do nothing
                        dialog.dismiss(); // Dismiss the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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
                displayQuizzesFrag();
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
                _likes = String.valueOf(quiz.getLikes());
                likes.setText(_likes);
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
    public void displayQuizzesFrag(){
        F_Quizzes fQuizzes = new F_Quizzes();

        // Replace the current fragment with the deal details fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fQuizzes)
                .addToBackStack(null)
                .commit();
    }
    public void displayQuizFieldFrag(){
        F_QuizField fQuizField = new F_QuizField();
        Bundle bundle = new Bundle();
        bundle.putSerializable("quiz", quiz);
        fQuizField.setArguments(bundle);

        // Replace the current fragment with the deal details fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fQuizField)
                .addToBackStack(null)
                .commit();
    }
}