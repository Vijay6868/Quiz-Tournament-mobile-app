package com.example.myapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsRESTAPI {
    @GET("api.php?amount=10&category=9&difficulty=medium&type=multiple")
    Call<Questions> getQuestions();
}

