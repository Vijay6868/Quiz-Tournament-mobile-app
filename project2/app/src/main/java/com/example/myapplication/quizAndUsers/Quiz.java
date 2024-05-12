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

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public QuestionsModelList getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionsModelList questions) {
        this.questions = questions;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}


