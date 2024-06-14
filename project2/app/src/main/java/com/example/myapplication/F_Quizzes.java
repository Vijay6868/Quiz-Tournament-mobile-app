package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.myapplication.quizAndUsers.QCompletedcallback;
import com.example.myapplication.quizAndUsers.Quiz;
import com.example.myapplication.quizAndUsers.UserManager;
import com.example.myapplication.recyclerview.RVAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class F_Quizzes extends Fragment implements SelectedListener {

    public F_Quizzes() {
        // Required empty public constructor
    }

    //============================================================================================
    Spinner sp_quiz_selection;
    View view;
    ArrayList<Quiz> quizArrayList;
    RecyclerView recyclerView;
    RVAdapter adapter;
    UserManager userManager;
    ArrayList<String> completedQuizzes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.f__quizzes, container, false);
        handleQuizSelectionSpin();
        readQuizData();
        recView();
        handlebtSearch();
        userManager = UserManager.getInstance();
        return view;
    }

    private void handlebtSearch() {
        FloatingActionButton btSearch = view.findViewById(R.id.bt_search);
        btSearch.setOnClickListener(v -> {
            
        });
    }

    // filter quizzes in recycler view based on user selection
    public void handleQuizSelectionSpin() {
        sp_quiz_selection = view.findViewById(R.id.sp_quizzes);

        sp_quiz_selection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                filterQuizzes(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayList<String> options = new ArrayList<>();
        options.add("ALL QUIZZES");
        options.add("COMING QUIZZES");
        options.add("PAST QUIZZES");
        options.add("ONGOING QUIZZES");
        options.add("COMPLETED QUIZZES");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_layout, options);
        sp_quiz_selection.setAdapter(adapter);
    }
    // add quiz items to arraylist based on user selection
    public void filterQuizzes(String filter) {
        // Fetch completed quizzes first
        compQuizzes(new CompletedQuizzesCallback() {
            @Override
            public void onCompletedQuizzesFetched() {
                ArrayList<Quiz> filteredQuizzes = new ArrayList<>();
                Validator validator = new Validator();

                for (Quiz quiz : quizArrayList) {
                    switch (filter) {
                        case "PAST QUIZZES":
                            if (!validator.isValidDate(quiz.getEdate())) {
                                filteredQuizzes.add(quiz);
                            }
                            break;
                        case "ONGOING QUIZZES":
                            if ((validator.isToday(quiz.getEdate()) || validator.isDateGreaterThanToday(quiz.getEdate())) &&
                                    validator.isDateTodayOrSmaller(quiz.getSdate())) {
                                filteredQuizzes.add(quiz);
                            }
                            break;
                        case "COMING QUIZZES":
                            if (validator.isDateGreaterThanToday(quiz.getSdate())) {
                                filteredQuizzes.add(quiz);
                            }
                            break;
                        case "COMPLETED QUIZZES":
                            if (completedQuizzes.contains(quiz.getQuiz_id())) {
                                filteredQuizzes.add(quiz);
                            }
                            break;
                        default:
                            filteredQuizzes.add(quiz);
                            break;
                    }
                }
                adapter.updateQuizzesList(filteredQuizzes);
            }
        });
    }
    // check if user has already particpated in the quiz
    public void compQuizzes(CompletedQuizzesCallback callback) {
        completedQuizzes = new ArrayList<>();
        userManager.fetchCompletedQuizzes(new QCompletedcallback() {
            @Override
            public void quizCompleted(ArrayList<String> compQuizzes) {
                completedQuizzes = compQuizzes;
                callback.onCompletedQuizzesFetched();
            }
        });
    }
    // read quiz data from firebase
    private void readQuizData() {
        quizArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Quizzes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String quiz_id = data.child("quiz_id").getValue().toString();
                    String noOfQues = data.child("noOfQues").getValue(String.class);
                    String sdate = data.child("sdate").getValue(String.class);
                    String edate = data.child("edate").getValue(String.class);
                    String qname = data.child("qname").getValue(String.class);
                    String difficulty = data.child("difficulty").getValue(String.class);
                    String type = data.child("type").getValue(String.class);
                    String category = data.child("category").getValue(String.class);
                    int likes = data.child("likes").getValue(Integer.class);

                    Quiz singleQuiz = new Quiz(qname, quiz_id, sdate, edate, likes, type, difficulty, category, noOfQues);
                    quizArrayList.add(singleQuiz);
                }
                recView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    //display quizzes in recycler view
    public void recView() {
        recyclerView = view.findViewById(R.id.f_rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RVAdapter(quizArrayList, this);
        recyclerView.setAdapter(adapter);
    }
    // handles when user click on quiz card
    @Override
    public void onItemClicked(Quiz quiz) {
        F_Quiz_Details fQuizDetails = new F_Quiz_Details();
        Bundle bundle = new Bundle();
        bundle.putSerializable("quiz", quiz);
        fQuizDetails.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fQuizDetails)
                .addToBackStack(null)
                .commit();
    }
    // Define the CompletedQuizzesCallback interface
    public interface CompletedQuizzesCallback {
        void onCompletedQuizzesFetched();
    }
}
