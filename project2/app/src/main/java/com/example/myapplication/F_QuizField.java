package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.QuestionModel;
import com.example.myapplication.quizAndUsers.Quiz;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class F_QuizField extends Fragment {


    Quiz quiz;
    View view;

    String quizID,quizType;
    TextView question, option1, option2, option3,option4,_true,_false, correct,incorrect;
    TextView quNo,tv_score;
    ArrayList<QuestionModel> questionArrayList;
    RadioGroup rg1, rg2;
    Button btNext, btCheck;
    QuestionModel _question;
    String playerSelection;
    String selOption, correctAns,_score;
    int score;

    public F_QuizField() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__quiz_field, container, false);
         question = view.findViewById(R.id.tv_question);
         option1 = view.findViewById(R.id.option1);
         option2 = view.findViewById(R.id.option2);
         option3 = view.findViewById(R.id.option3);
         option4 = view.findViewById(R.id.option4);
         _true = view.findViewById(R.id.rb_true);
         _false = view.findViewById(R.id.rb_false);
         rg1 = view.findViewById(R.id.rg_multiple);
         rg2 = view.findViewById(R.id.rg_true_or_false);
         correct = view.findViewById(R.id.tv_correct);
         incorrect = view.findViewById(R.id.tv_incorrect);
         btNext = view.findViewById(R.id.btNext);
         quNo = view.findViewById(R.id.tv_que_no);
         btCheck = view.findViewById(R.id.btCheck);
         tv_score = view.findViewById(R.id.score);


         fetchQuestions();
         quizType = quiz.getType();
         displayQuestionOptions(quizType);
         handlePlayerSelection();
         handleBtCheck();
         setScore();
         score =0;
        return view;
    }
    public void setScore(){
        _score = String.valueOf(score);
        tv_score.setText(_score);
    }

    private void handleBtCheck() {
        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( selOption != null){
                    if(selOption.equals(correctAns)){
                        correct.setVisibility(View.VISIBLE);
                        incorrect.setVisibility(View.INVISIBLE);
                        score = score+1;
                    }else {
                        incorrect.setVisibility(View.VISIBLE);
                        correct.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), "make selection", Toast.LENGTH_SHORT).show();
                }
                //selOption = null;
            }
        });
    }

    private String handlePlayerSelection() {

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId!= -1){
                    RadioButton player_selection = view.findViewById(checkedId);
                    selOption = player_selection.getText().toString();
                }

                //Toast.makeText(getContext(), "Selected: " + selOption, Toast.LENGTH_SHORT).show();
            }

        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId!= -1){
                    RadioButton player_selection = view.findViewById(checkedId);
                    selOption = player_selection.getText().toString();
                }

            }
        });
        return selOption;
    }


    private void handleBtNext() {

        int firstQue =1;
        QuestionModel singleQuestion = questionArrayList.get(0);
        String que = singleQuestion.getQuestion();
        question.setText(que);
        correctAns = singleQuestion.getCorrect_answer();
        shuffleOptions(singleQuestion.getCorrect_answer(),singleQuestion.getIncorrectAnswers());
        handleBtCheck();

        btNext.setOnClickListener(new View.OnClickListener() {

            int index=1;


            @Override
            public void onClick(View v) {
                setScore();
               if(index < questionArrayList.size()){
                   _question = questionArrayList.get(index);
                   question.setText(_question.getQuestion());
                   correctAns= _question.getCorrect_answer();
                   shuffleOptions(correctAns, _question.getIncorrectAnswers());

//                   if((index+1)==questionArrayList.size()){
//                       btNext.setText("FINISH");
//                   }




                   answerCheckDialog();
                   clearSelection();
                   if((index+1)==questionArrayList.size()){
                       btNext.setText("FINISH");
                   }
                   index++;
                   quNo.setText("Question: "+index);
               }
               else {
                   displayScoreFrag();
               }
                selOption = null;

            }
        });
    }
    public void displayScoreFrag(){
        F_Display_Score fDisplayScore = new F_Display_Score();
        //fDisplayScore.setArguments();
        Bundle bundle = new Bundle();
        bundle.putInt("final_score", score);
        fDisplayScore.setArguments(bundle);

        // Replace the current fragment with the deal details fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fDisplayScore)
                .addToBackStack(null)
                .commit();
    }
    public void clearSelection(){
        if(quizType.equals("multiple")){
            rg1.clearCheck();
        }
        else{
            rg2.clearCheck();
        }
    }
    public void shuffleOptions(String correct, ArrayList<String> _incorrect){
        if(quiz.getType().equals("multiple")){
            _incorrect.add(correct);
            Collections.shuffle(_incorrect);
            option1.setText(_incorrect.get(0));
            option2.setText(_incorrect.get(1));
            option3.setText(_incorrect.get(2));
            option4.setText((_incorrect.get(3)));
        }

    }
    private void displayQuestionOptions(String category) {
        if(category.equals("multiple")){
            rg2.setVisibility(View.GONE);
        }
        else {
            rg1.setVisibility(View.GONE);
        }
        answerCheckDialog();
    }
    public void answerCheckDialog(){
        correct.setVisibility(View.GONE);
        incorrect.setVisibility(View.GONE);
        

    }

    private void fetchQuestions() {
        questionArrayList = new ArrayList<>();
        Bundle bundle = getArguments();
        if(bundle !=null) {
            quiz = (Quiz) bundle.getSerializable("quiz");
            // use quiz object to populate the quiz_details fields
            if (quiz != null) {
                quizID = quiz.getQuiz_id();

                FirebaseDatabase.getInstance().getReference().child("Questions").child(quizID)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot data: snapshot.getChildren()){
                                    String question = data.child("question").getValue(String.class);
                                    String correct_answer = data.child("correct_answer").getValue(String.class);
                                    ArrayList<String> incorrect_answers = new ArrayList<>();
                                    for(DataSnapshot incorrectAnswerSnapshot: data.child("incorrect_answers").getChildren()){
                                        String incorrectAnswer = incorrectAnswerSnapshot.getValue(String.class);
                                        incorrect_answers.add(incorrectAnswer);
                                    }
                                    QuestionModel singleQuestion = new QuestionModel(question, correct_answer,incorrect_answers);
                                    questionArrayList.add(singleQuestion);
                                }
                                handleBtNext();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

            }
        }
    }
}