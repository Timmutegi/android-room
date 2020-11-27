package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {
    private AppDatabase db;
    private StudentsAdapter adapter;
    private RecyclerView recyclerView;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Bundle bundle = getIntent().getExtras();
        int value;
        if(bundle != null) {
            value = bundle.getInt("note");
            Log.e("test", "id " + value);
        }
        initialize();
        displayList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void initialize() {
        recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentsActivity.this));
        students = new ArrayList<>();
        adapter = new StudentsAdapter(students, StudentsActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void displayList(){
        // initialize database instance
        db = AppDatabase.getInstance(StudentsActivity.this);
        // fetch list of notes in background thread
        new StudentsActivity.RetrieveTask(this).execute();
    }

    private static class RetrieveTask extends AsyncTask<Void, Void, List<Student>> {
        private WeakReference<StudentsActivity> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(StudentsActivity context) {
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected List<Student> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().db.studentsDao().getAll();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            if (students!=null && students.size()>0 ){
                activityReference.get().students = students;

                // create and set the adapter on RecyclerView instance to display list
                activityReference.get().adapter = new StudentsAdapter(students,activityReference.get());
                activityReference.get().recyclerView.setAdapter(activityReference.get().adapter);
            }
        }
    }
}