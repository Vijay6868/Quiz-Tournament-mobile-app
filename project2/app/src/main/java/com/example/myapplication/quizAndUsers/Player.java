package com.example.myapplication.quizAndUsers;

import java.util.ArrayList;

public class Player extends Users {

    ArrayList<String> quizzes_completed;

    public Player(String uid, String userType, ArrayList<String> quizzes_completed) {
        super(uid, userType);
        this.quizzes_completed = quizzes_completed;
    }

    public ArrayList<String> getQuizzes_completed() {
        return quizzes_completed;
    }

    public void setQuizzes_completed(ArrayList<String> quizzes_completed) {
        this.quizzes_completed = quizzes_completed;
    }
}
