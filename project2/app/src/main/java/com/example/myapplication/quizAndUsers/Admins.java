package com.example.myapplication.quizAndUsers;

import java.util.ArrayList;

public class Admins extends Users{
    ArrayList<String> quizzes_completed;

    public Admins(String uid, String userType, ArrayList<String> quizzes_completed) {
        super(uid, userType);
        this.quizzes_completed = quizzes_completed;
    }
}
