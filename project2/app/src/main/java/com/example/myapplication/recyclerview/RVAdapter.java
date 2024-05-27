package com.example.myapplication.recyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SelectedListener;
import com.example.myapplication.quizAndUsers.Quiz;

import java.util.ArrayList;
import java.util.Random;

public class RVAdapter extends RecyclerView.Adapter<RVHolder>{
    private ArrayList<Quiz> quizArrayList;
    private SelectedListener listener;
    private int[] imageResources = {R.drawable.cardimg5, R.drawable.cardimg6
            ,R.drawable.cardimg7,R.drawable.cardimg8, R.drawable.cardimg9};
    private int[] cardColors = {R.color.bt_color_2, R.color.bt_color_3, R.color.bt_color_1};

    //private SelectListener listener;
    public RVAdapter(ArrayList<Quiz> quizArrayList, SelectedListener listener) {
        this.quizArrayList = quizArrayList;
        this.listener = listener;
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

        String s_date = "Starts: ";
        String e_date = "Ends: ";

        Quiz item = quizArrayList.get(position);
        holder.qname.setText(item.getQname());
        holder.sdate.setText(s_date+item.getSdate());
        holder.edate.setText((e_date+item.getEdate()));

        int colorIndex = position % cardColors.length;
        int colorRes = cardColors[colorIndex];
        //randomly assign the color to card
        holder.rl_color.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), colorRes));

        Random random = new Random();
        int randomIndex = random.nextInt(imageResources.length);

        // Set the randomly selected image
        holder.cardimg.setImageResource(imageResources[randomIndex]);

        //handle when user clicks on card
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }
    // update quiz list as per selection in dropdown in F_quizzes fragment
    public void updateQuizzesList(ArrayList<Quiz> updatedList){
        quizArrayList = new ArrayList<>(updatedList);
        notifyDataSetChanged();
    }
}

