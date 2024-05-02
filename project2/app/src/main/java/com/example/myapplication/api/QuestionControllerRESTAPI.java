package com.example.myapplication.api;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class QuestionControllerRESTAPI implements retrofit2.Callback<Question>{

    final String BASE_URL = "https://opentdb.com/api.php?amount=10&category=9&difficulty=medium&type=multiple";
    private Questions questions;
    public void start(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuestionRESTAPI questionsRESTAPI = retrofit.create(QuestionRESTAPI.class);
        Call<Questions> call = questionsRESTAPI.getQuestions();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Question> call, Response<Question> response) {
        if(response.isSuccessful()){
            questions = response.body();
            Log.d("QUESTIONS_COUNT"," QUESTION :"+ questions.data.size());
        }
    }

    @Override
    public void onFailure(Call<Question> call, Throwable t) {

    }
}
