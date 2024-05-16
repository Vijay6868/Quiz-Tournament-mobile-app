package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.api.QuestionModel;
import com.example.myapplication.quizAndUsers.Quiz;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class F_QuizField extends Fragment {


    Quiz quiz;
    View view;

    String quizID;
    ArrayList<QuestionModel> questionArrayList;
    public F_QuizField() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__quiz_field, container, false);
         fetchQuestions();
        return view;
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


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

            }
        }
    }
}