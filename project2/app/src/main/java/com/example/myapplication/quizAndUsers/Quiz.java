package com.example.myapplication.quizAndUsers;

import com.example.myapplication.api.QuestionsModelList;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
public class Quiz implements Serializable {
    String qname;
    String quiz_id;
    String sdate,edate;
    QuestionsModelList questions;
    int likes;
    String type, difficulty,category,noOfQues;

    public Quiz(String qname, String quiz_id, String sdate, String edate, int likes, String type,
                String difficulty, String category, String noOfQues) {
        this.qname = qname;
        this.quiz_id = quiz_id;
        this.sdate = sdate;
        this.edate = edate;
        this.likes = likes;
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.noOfQues = noOfQues;
    }

    public Quiz(String qname, String sdate, String edate, String type, String difficulty,
                String category, String noOfQues, QuestionsModelList questions) {
        this.qname = qname;
        this.sdate = sdate;
        this.edate = edate;
        this.questions = questions;
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.noOfQues = noOfQues;
        likes =0;

        Random random = new Random();
        int gen_quiz_id = random.nextInt(100);
        quiz_id = "quiz_id: "+gen_quiz_id;

    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNoOfQues() {
        return noOfQues;
    }

    public void setNoOfQues(String noOfQues) {
        this.noOfQues = noOfQues;
    }
}


