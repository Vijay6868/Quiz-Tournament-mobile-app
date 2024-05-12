package com.example.myapplication.quizAndUsers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import com.example.myapplication.api.DataCallback;
import com.example.myapplication.api.QuestionModel;
import com.example.myapplication.api.QuestionModelControllerAPI;
import com.example.myapplication.api.QuestionsModelList;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class QuizManager {
    Quiz quiz;

    public QuizManager(Quiz quiz) {
        this.quiz = quiz;
        insertQuizData();
    }
    public void insertQuizData(){
        HashMap<String, Object> quiz_data = new HashMap<>();

        quiz_data.put("quiz_id",quiz.getQuiz_id());
        quiz_data.put("qname",quiz.getQname());
        quiz_data.put("sdate",quiz.getSdate());
        quiz_data.put("edate",quiz.getEdate());
        quiz_data.put("likes",quiz.getLikes());

        String quizID = Integer.toString(quiz.getQuiz_id());

        FirebaseDatabase.getInstance().getReference().child("Quizzes").child(quizID).setValue(quiz_data);
    }
}
