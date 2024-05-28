package com.example.myapplication.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class RVHolder extends RecyclerView.ViewHolder {
TextView qname, sdate,edate, category, difficulty;
RelativeLayout rl_color;
ImageView cardimg;
CardView cardView;

    public RVHolder(@NonNull View itemView) {
        super(itemView);

        qname = itemView.findViewById(R.id.qname);
        sdate = itemView.findViewById(R.id.start_date);
        edate = itemView.findViewById(R.id.end_date);
        rl_color = itemView.findViewById(R.id.rl_color);
        cardimg = itemView.findViewById(R.id.card_img);
        cardView = itemView.findViewById(R.id.rv_carditems);
        category = itemView.findViewById(R.id.tv_category);
        difficulty = itemView.findViewById(R.id.tv_difficulty);

    }
}
