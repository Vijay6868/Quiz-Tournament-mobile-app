package com.example.myapplication.api;

/*"type": "multiple",
        "difficulty": "medium",
        "category": "General Knowledge",
        "question": "When was the Declaration of Independence approved by the Second Continental Congress?",
        "correct_answer": "July 4, 1776",
        "incorrect_answers": [
        "May 4, 1776",
        "June 4, 1776",
        "July 2, 1776"
        ]*/

public class Question {
    String question;
    String correctAns;
    String wrongAns;

    public Question(String question, String correctAns, String wrongAns) {
        this.question = question;
        this.correctAns = correctAns;
        this.wrongAns = wrongAns;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(String wrongAns) {
        this.wrongAns = wrongAns;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", correctAns='" + correctAns + '\'' +
                ", wrongAns='" + wrongAns + '\'' +
                '}';
    }
}
