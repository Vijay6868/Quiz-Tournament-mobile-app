package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.quizAndUsers.Quiz;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Quiz_Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F_Quiz_Details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F_Quiz_Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Quiz_Details.
     */
    // TODO: Rename and change types and number of parameters
    public static F_Quiz_Details newInstance(String param1, String param2) {
        F_Quiz_Details fragment = new F_Quiz_Details();
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
    //================================================================================
    View view;
    EditText qname, sdate,edate;
    TextView difficulty, category, noOfQues;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.f_quiz_details, container, false);
         qname = view.findViewById(R.id.tbTitle);
         difficulty = view.findViewById(R.id.spin_difficulty);
         category = view.findViewById(R.id.spin_category);
         noOfQues = view.findViewById(R.id.spin_ques_type);
         sdate = view.findViewById(R.id.start_date);
         edate = view.findViewById(R.id.end_date);

         setQuizDetails();

        return view;
    }

    public void setQuizDetails(){
        Bundle bundle = getArguments();

        if(bundle !=null){
            Quiz quiz = (Quiz) bundle.getSerializable("quiz");
            // use quiz object to populate the quiz_details fields
            if(quiz != null){
                qname.setText(quiz.getQname());
                difficulty.setText(quiz.getDifficulty());
                category.setText(quiz.getCategory());
                noOfQues.setText(quiz.getNoOfQues());
                sdate.setText(quiz.getSdate());
                edate.setText(quiz.getEdate());
            }

        }
    }
}