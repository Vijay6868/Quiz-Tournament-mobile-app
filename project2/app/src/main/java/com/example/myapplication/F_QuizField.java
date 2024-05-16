package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    String quizID;
    TextView question, option1, option2, option3,option4,_true,_false, correct,incorrect;
    TextView quNo;
    ArrayList<QuestionModel> questionArrayList;
    RadioGroup rg1, rg2;
    Button btNext;
    QuestionModel _question;
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


         fetchQuestions();

         displayQuestionOptions(quiz.getType());

        return view;
    }

    private void handleBtNext() {

        QuestionModel singlequestion = questionArrayList.get(0);
        String que = singlequestion.getQuestion();
        question.setText(que);
        shuffleOptions(singlequestion.getCorrect_answer(),singlequestion.getIncorrectAnswers());

        btNext.setOnClickListener(new View.OnClickListener() {
            int index=1;
            int score =0;

            @Override
            public void onClick(View v) {
               if(index < questionArrayList.size()){
                   _question = questionArrayList.get(index);
                   question.setText(_question.getQuestion());
                   shuffleOptions(_question.getCorrect_answer(), _question.getIncorrectAnswers());
                   index++;
                   quNo.setText("Question: "+index);
               }

            }
        });
    }
    public void shuffleOptions(String correct, ArrayList<String> _incorrect){
        _incorrect.add(correct);
        Collections.shuffle(_incorrect);
        option1.setText(_incorrect.get(0));
        option2.setText(_incorrect.get(1));
        option3.setText(_incorrect.get(2));
        option4.setText((_incorrect.get(3)));
    }

    private void displayQuestionOptions(String category) {
        if(category.equals("multiple")){
            rg2.setVisibility(View.GONE);
        }
        else {
            rg1.setVisibility(View.GONE);
        }
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