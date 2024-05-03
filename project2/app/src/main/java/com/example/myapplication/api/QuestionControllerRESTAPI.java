package com.example.myapplication.api;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class QuestionControllerRESTAPI implements retrofit2.Callback<Questions>{

    final String BASE_URL = "https://opentdb.com/";
    private Questions questions;
    public void start(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuestionsRESTAPI questionsRESTAPI = retrofit.create(QuestionsRESTAPI.class);
        Call<Questions> call = questionsRESTAPI.getQuestions();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Questions> call, Response<Questions> response) {
        if(response.isSuccessful()){
            questions = response.body();
            Log.d("QUESTIONS_COUNT"," QUESTION :"+ questions.data.size());
            List<Question> questionList = questions.getData();

            if(questionList!=null)
                for (Question q: questionList){
                    Log.d("QUESTION_INFO"," QUESTION :"+ q.toString());
                }
            else
                Log.d("QUESTION_INFO"," QUESTION's List empty");
            Log.d("QUESTION_Count"," QUESTION Count- "+ questions.data.size());
        }
    }

    @Override
    public void onFailure(Call<Questions> call, Throwable t) {
        t.printStackTrace();
        Log.d("QUESTION_INFO","Error getting users");
    }
    public Questions getUsers() {
        if( questions !=null)
            Log.d("QUESTION_Count"," Question Count--"+ questions.data.size());
        else
            Log.d("QUESTION_Count"," Question object is null");

        return questions;

    }
}
