package com.example.myapplication.quizAndUsers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import com.example.myapplication.api.DataCallback;
import com.example.myapplication.api.QuestionModel;
import com.example.myapplication.api.QuestionModelControllerAPI;
import com.example.myapplication.api.QuestionsModelList;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
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

        QuestionsModelList questions_list= quiz.getQuestions();

        //HashMap<String, Object> question_data = new HashMap<>();
        for(QuestionModel q : questions_list.getQuestionModelList()){

            ArrayList<String> incorrect_answers = new ArrayList<>();
            incorrect_answers = parseJsonArray(q.getIncorrect_answers());

            HashMap<String, Object> question_data = new HashMap<>();
            question_data.put("type",q.getType());
            question_data.put("difficulty",q.getDifficulty());
            question_data.put("question",q.getQuestion());
            question_data.put("correct_answer",q.getCorrect_answer());

            question_data.put("incorrect_answers",incorrect_answers);

            FirebaseDatabase.getInstance().getReference().child("Questions").child(quizID).setValue(question_data);
        }


    }
    public ArrayList<String> parseJsonArray(JSONArray jsonArray){
        ArrayList<String> incorrect_answers = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                //String val = String.valueOf(jsonArray.getInt(i));
                incorrect_answers.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return incorrect_answers;
    }
}
