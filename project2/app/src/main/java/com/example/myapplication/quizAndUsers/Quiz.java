package com.example.myapplication.quizAndUsers;

import com.example.myapplication.api.QuestionsModelList;

import java.util.Date;
import java.util.Random;
public class Quiz {
    String qname;
    int quiz_id;
    Date sdate,edate;
    QuestionsModelList questions;
    int likes;

    public Quiz(String qname, Date sdate, Date edate, QuestionsModelList questions) {
        this.qname = qname;
        this.sdate = sdate;
        this.edate = edate;
        this.questions = questions;
        likes =0;

        Random random = new Random();
        quiz_id = random.nextInt(100);

    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

/*    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }*/
}
