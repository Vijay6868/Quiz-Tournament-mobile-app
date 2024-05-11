package com.example.myapplication.quizAndUsers;

import com.example.myapplication.api.DataCallback;
import com.example.myapplication.api.QuestionModelControllerAPI;
import com.example.myapplication.api.QuestionsModelList;

public class QuizManager implements DataCallback {
    QuestionModelControllerAPI questionModelControllerAPI;
    public QuizManager(){
        //questionModelControllerAPI = new QuestionModelControllerAPI(this,this);
    }
    @Override
    public void onDataLoaded(QuestionsModelList list) {

    }
}
