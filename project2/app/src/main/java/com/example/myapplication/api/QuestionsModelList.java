package com.example.myapplication.api;

import java.util.ArrayList;

public class QuestionsModelList {
    private ArrayList<QuestionModel> questionModelList;

    public QuestionsModelList() {
        this.questionModelList = new ArrayList<>();
    }

    public void addUserModel(QuestionModel questionModel) {
        questionModelList.add(questionModel);
    }

    public void removeUserModel(QuestionModel questionModel) {
        questionModelList.remove(questionModel);
    }

    public ArrayList<QuestionModel> getQuestionModelList() {
        return questionModelList;
    }

    public void setQuestionModelList(ArrayList<QuestionModel> questionModelList) {
        this.questionModelList = questionModelList;
    }
}



