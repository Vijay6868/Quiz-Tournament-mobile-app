package com.example.myapplication.api;

import java.util.ArrayList;

public class QuestionsModelList {
    private ArrayList<QuestionModel> userModelList;

    public QuestionsModelList() {
        this.userModelList = new ArrayList<>();
    }

    public void addUserModel(QuestionModel questionModel) {
        userModelList.add(questionModel);
    }

    public void removeUserModel(QuestionModel questionModel) {
        userModelList.remove(questionModel);
    }

    public ArrayList<QuestionModel> getUserModelList() {
        return userModelList;
    }

    public void setUserModelList(ArrayList<QuestionModel> questionModelList) {
        this.userModelList = questionModelList;
    }
}



