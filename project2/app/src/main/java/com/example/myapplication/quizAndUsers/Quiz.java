package com.example.myapplication.quizAndUsers;

import com.example.myapplication.api.QuestionsModelList;

import java.util.Date;
import java.util.Random;
public class Quiz {
    String qname;
    int quiz_id;
    String sdate,edate;
    QuestionsModelList questions;
    int likes;

    public Quiz(String qname, String sdate, String edate, QuestionsModelList questions) {
        this.qname = qname;
        this.sdate = sdate;
        this.edate = edate;
        this.questions = questions;
        likes =0;

        Random random = new Random();
        quiz_id = random.nextInt(100);

    }


}


