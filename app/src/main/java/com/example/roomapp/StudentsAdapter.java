package com.example.roomapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.BeanHolder> {
    private List<Student> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public StudentsAdapter(List<Student> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public StudentsAdapter.BeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.student_score, parent, false);
        return new StudentsAdapter.BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentsAdapter.BeanHolder holder, int position) {
//        Log.e("bind", "onBindViewHolder: " + list.get(position));
        if(list != null) {
            Student student =  list.get(position);
            holder.textViewID.setText("Student: " + Integer.toString(student.getStudentId()));
            holder.textViewQ1.setText("Q1: " + Integer.toString(student.getQ1()));
            holder.textViewQ2.setText("Q2: " + Integer.toString(student.getQ2()));
            holder.textViewQ3.setText("Q3: " + Integer.toString(student.getQ3()));
            holder.textViewQ4.setText("Q4: " + Integer.toString(student.getQ4()));
            holder.textViewQ5.setText("Q5: " + Integer.toString(student.getQ5()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder {

        TextView textViewQ1;
        TextView textViewID;
        TextView textViewQ2;
        TextView textViewQ3;
        TextView textViewQ4;
        TextView textViewQ5;

        public BeanHolder(View itemView) {
            super(itemView);
            textViewQ1 = itemView.findViewById(R.id.student_Q1);
            textViewID = itemView.findViewById(R.id.student_id);
            textViewQ2 = itemView.findViewById(R.id.student_Q2);
            textViewQ3 = itemView.findViewById(R.id.student_Q3);
            textViewQ4 = itemView.findViewById(R.id.student_Q4);
            textViewQ5 = itemView.findViewById(R.id.student_Q5);
        }

    }
}
