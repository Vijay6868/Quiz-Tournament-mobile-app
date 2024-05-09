package com.example.myapplication.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//https://opentdb.com/api.php?amount=10&category=9&difficulty=medium&type=multiple


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

public class QuestionModelControllerAPI {
    private QuestionsModelList list;
    private QuestionModel singleModel;
    private DataCallback callback;
    private String url = "https://opentdb.com/api.php?amount=10&category=9&difficulty=medium&type=boolean";

            //"https://opentdb.com/api.php?amount=10&category=9&difficulty=medium";
    private Context context;

    public QuestionModelControllerAPI(Context context, DataCallback callback) {
        this.context = context;
        this.callback = callback;
        list = new QuestionsModelList();
    }

    public void getData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("api", "onResponse: " + response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray resultsArray = jsonResponse.getJSONArray("results");
                            for (int i = 0; i < resultsArray.length(); i++) {
                                JSONObject singleObject = resultsArray.getJSONObject(i);
                                singleModel = new QuestionModel(
                                        singleObject.getString("type"),
                                        singleObject.getString("difficulty"),
                                        singleObject.getString("category"),
                                        singleObject.getString("question"),
                                        singleObject.getString("correct_answer"),
                                        singleObject.getJSONArray("incorrect_answers")
                                );
                                list.addUserModel(singleModel); // Add the model to the list
                            }
                            callback.onDataLoaded(list); // Notify the callback
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api", "VolleyError: " + error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}


