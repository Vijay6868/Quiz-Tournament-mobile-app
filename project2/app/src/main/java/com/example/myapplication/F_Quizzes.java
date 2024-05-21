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
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.quizAndUsers.Quiz;
import com.example.myapplication.recyclerview.RVAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Quizzes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F_Quizzes extends Fragment implements SelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F_Quizzes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Quizzes.
     */
    // TODO: Rename and change types and number of parameters
    public static F_Quizzes newInstance(String param1, String param2) {
        F_Quizzes fragment = new F_Quizzes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
//============================================================================================
    Spinner sp_quiz_selection;
    View view;
    ArrayList<Quiz>  quizArrayList;
    //ArrayList<String> singleQuiz;
    Quiz singleQuiz;
    RecyclerView recyclerView;
    RVAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f__quizzes, container, false);
        handleQuizSelectionSpin();
        readQuizData();
        recView();
        return view;
    }

    public void handleQuizSelectionSpin(){
        sp_quiz_selection = view.findViewById(R.id.sp_quizzes);

        sp_quiz_selection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                filterQuizzes(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayList<String> options = new ArrayList<>();
        options.add("ALL QUIZZES");
        options.add("COMING QUIZZES");
        options.add("PAST QUIZZES");
        options.add("ONGOING QUIZZES");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_layout, options);

        sp_quiz_selection.setAdapter(adapter);
    }
    public void filterQuizzes(String filter) {
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
                default:
                    filteredQuizzes.add(quiz);
                    break;
            }
        }
        adapter.updateQuizzesList(filteredQuizzes);
    }

    private void readQuizData() {
        //singleQuiz = new ArrayList<>();
        quizArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Quizzes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){

                        String quiz_id = data.child("quiz_id").getValue().toString();
                        String noOfQues = data.child("noOfQues").getValue(String.class);
                        String sdate = data.child("sdate").getValue(String.class);
                        String edate = data.child("edate").getValue(String.class);
                        String qname = data.child("qname").getValue(String.class);
                        String difficulty = data.child("difficulty").getValue(String.class);
                        String type = data.child("type").getValue(String.class);
                        String category = data.child("category").getValue(String.class);
                        int likes = data.child("likes").getValue(Integer.class);

                        singleQuiz = new Quiz(qname,quiz_id,sdate,edate,likes,type,
                                difficulty,category,noOfQues);
                        quizArrayList.add(singleQuiz);
                }
                quizArrayList.size();
                recView();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void recView(){
        recyclerView = view.findViewById(R.id.f_rv_item);
        adapter = new RVAdapter(quizArrayList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onItemClicked(Quiz quiz) {
        F_Quiz_Details fQuizDetails = new F_Quiz_Details();

        Bundle bundle = new Bundle();
        bundle.putSerializable("quiz", quiz);
        fQuizDetails.setArguments(bundle);

        // Replace the current fragment with the deal details fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fQuizDetails)
                .addToBackStack(null)
                .commit();
    }
}