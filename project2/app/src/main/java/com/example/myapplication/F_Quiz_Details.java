package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
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

import com.example.myapplication.quizAndUsers.LikeCallback;
import com.example.myapplication.quizAndUsers.QCompletedcallback;
import com.example.myapplication.quizAndUsers.Quiz;
import com.example.myapplication.quizAndUsers.QuizManager;
import com.example.myapplication.quizAndUsers.UserManager;
import com.example.myapplication.quizAndUsers.UserTypeCallback;

import java.util.ArrayList;
import java.util.Calendar;


public class F_Quiz_Details extends Fragment {

    View view;
    EditText qname;
    TextView difficulty, category, noOfQues, w_sdate, w_edate, sdate,edate,w_title,likes;
    Button btClose, btStart, btUpdate, btComing, btQuizOver, btCompleted;
    String _start_date, _end_date,_qname,_likes, userType;
    Validator validator;
    Quiz quiz;
    QuizManager quizManager;
    ImageView iv_delete;
    ToggleButton tgLike;
    int originalLikes;
    UserManager userManager;
    LoadingDialog loadingDialog;

    public F_Quiz_Details() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f_quiz_details, container, false);
            loadDialog();

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
         btQuizOver = view.findViewById(R.id.btQuizOver);
         btComing = view.findViewById(R.id.btComing);
         btCompleted = view.findViewById(R.id.btCompleted);
         setQuizDetails();

         inputs();
         handbtStart();
         handleDeleteQuiz();
         handleBtClose();
         handleDateSelection();
         handleBtUpdate();
         handleTgLike();
         displayFeatures();


        quizManager = new QuizManager(quiz);
        userManager = UserManager.getInstance();
        checkIfQuizCompleted();
        tgLikeInitialState();
        originalLikes = quiz.getLikes();

        return view;
    }

    private void tgLikeInitialState() {
        userManager.isLiked(new LikeCallback() {
            @Override
            public void onCallback(boolean isLike) {
                tgLike.setChecked(isLike);
            }
        }, quiz.getQuiz_id());

    }
    private void checkIfQuizCompleted(){
        userManager.fetchCompletedQuizzes(new QCompletedcallback() {
            @Override
            public void quizCompleted(ArrayList<String> compQuizzes) {
                if(compQuizzes.contains(quiz.getQuiz_id())  ){
                    btCompleted.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void displayFeatures() {
        UserManager userManager = UserManager.getInstance();
        userManager.getUserType(new UserTypeCallback() {
            @Override
            public void onCallback(String user_Type) {
                userType = user_Type;
                if(userType.equals("Players")){
                    iv_delete.setVisibility(View.INVISIBLE);
                    btUpdate.setVisibility(View.INVISIBLE);
                    sdate.setEnabled(false);
                    edate.setEnabled(false);
                    qname.setEnabled(false);
                    pastQuiz();
                    futureQuiz();

                }
            }
        });

    }
    public void pastQuiz(){
        boolean check = validator.isValidDate(_end_date);
        if(!check){
            btStart.setVisibility(View.INVISIBLE);
            btQuizOver.setVisibility(View.VISIBLE);
        }
    }
    public void futureQuiz(){
        boolean check = false;
        if(validator.isValidDate(_start_date)){
            btStart.setVisibility(View.INVISIBLE);
            btComing.setVisibility(View.VISIBLE);
        }
        if(validator.isToday(_start_date)){
            btStart.setVisibility(View.VISIBLE);
            btComing.setVisibility(View.INVISIBLE);
        }

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
                    userManager.updatelikedQuizees(quiz.getQuiz_id(),true);
                    //likes
                }
                else{
                    String _likes = String.valueOf(originalLikes);
                    likes.setText(_likes);
                    int newLike = quiz.getLikes()-1;
                    quiz.setLikes(newLike);
                    userManager.updatelikedQuizees(quiz.getQuiz_id(),false);
                }
                quizManager.updateLikes();
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
                       // quizManager = new QuizManager(quiz);
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

                    //quizManager = new QuizManager(quiz);
                    quizManager.updateQuizData();
                    Toast.makeText(getContext(), "Quiz Updated", Toast.LENGTH_SHORT).show();
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
                difficulty.setText("Difficulty: "+ quiz.getDifficulty());
                category.setText("Category: "+quiz.getCategory());
                noOfQues.setText("No. of Questions: "+quiz.getNoOfQues());
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
    private void loadDialog() {
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        },1000);
    }
}