package com.example.myapplication.recyclerview;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class RVHolder extends RecyclerView.ViewHolder {
TextView qname, sdate,edate;
RelativeLayout rl_color;

    public RVHolder(@NonNull View itemView) {
        super(itemView);

        qname = itemView.findViewById(R.id.qname);
        sdate = itemView.findViewById(R.id.start_date);
        edate = itemView.findViewById(R.id.end_date);
        rl_color = itemView.findViewById(R.id.rl_color);

    }
}
