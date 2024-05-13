package com.example.myapplication.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.quizAndUsers.Quiz;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVHolder>{
    private ArrayList<Quiz> quizArrayList;
    //private SelectListener listener;
    public RVAdapter(ArrayList<Quiz> quizArrayList) {
        this.quizArrayList = quizArrayList;
    }

    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_carditems, parent, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolder holder, int position) {
        Quiz item = quizArrayList.get(position);
        holder.qname.setText(item.getQname());
        holder.sdate.setText(item.getSdate());
        holder.edate.setText((item.getEdate()));
    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }
}
