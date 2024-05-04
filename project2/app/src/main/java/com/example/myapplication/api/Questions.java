package com.example.myapplication.api;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    public Questions(List<Question> data) {
        this.data = data;
    }

    public List<Question> data;

    public List<Question> getData() {
        return data;
    }

    public void setData(List<Question> data) { this.data = data; }
}

