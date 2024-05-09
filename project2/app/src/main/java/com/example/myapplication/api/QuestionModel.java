package com.example.myapplication.api;


import org.json.JSONArray;

/*      {
              "type": "multiple",
              "difficulty": "medium",
              "category": "General Knowledge",
              "question": "This field is sometimes known as &ldquo;The Dismal Science.&rdquo;",
              "correct_answer": "Economics",
              "incorrect_answers": [
              "Philosophy",
              "Politics",
              "Physics"
              ]
              },*/
public class QuestionModel {

    String type;
    String difficulty;
    String category;
    String question;
    String correct_answer;
    JSONArray incorrect_answers;

    public QuestionModel(String type, String difficulty, String category, String question, String correct_answer, JSONArray incorrect_answers) {
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public JSONArray getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(JSONArray incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
