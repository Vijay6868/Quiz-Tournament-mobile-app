package com.example.myapplication.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class RVHolder extends RecyclerView.ViewHolder {
TextView qname, sdate,edate;

    public RVHolder(@NonNull View itemView) {
        super(itemView);

        qname = itemView.findViewById(R.id.qname);
        sdate = itemView.findViewById(R.id.start_date);
        edate = itemView.findViewById(R.id.end_date);

    }
}
