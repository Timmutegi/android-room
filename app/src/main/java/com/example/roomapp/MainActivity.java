package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnNoteItemClick {
    private AppDatabase db;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Course> courses;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        displayList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void initialize() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        courses = new ArrayList<>();
        adapter = new RecyclerViewAdapter(courses, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void displayList(){
        // initialize database instance
        db = AppDatabase.getInstance(MainActivity.this);
        // fetch list of notes in background thread
        new RetrieveTask(this).execute();
    }

    private static class RetrieveTask extends AsyncTask<Void, Void, List<Course>> {
        private WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected List<Course> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().db.coursesDao().getAll();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Course> courses) {
            if (courses!=null && courses.size()>0 ){
                activityReference.get().courses = courses;

                // create and set the adapter on RecyclerView instance to display list
                activityReference.get().adapter = new RecyclerViewAdapter(courses,activityReference.get());
                activityReference.get().recyclerView.setAdapter(activityReference.get().adapter);
            }
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(new Intent(MainActivity.this, StudentsActivity.class), 100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                courses.add((Course) data.getSerializableExtra("note"));
            } else if (resultCode == 2) {
                courses.set(pos, (Course) data.getSerializableExtra("note"));
            }
        }
    }

    @Override
    public void onNoteClick(final int pos) {
        MainActivity.this.pos = pos;

        startActivityForResult(
                new Intent(MainActivity.this,
                        StudentsActivity.class).putExtra("note", courses.get(pos)),
                100);
                Log.e("test1", "id " + pos);
    }

    @Override
    protected void onDestroy() {
        db.cleanUp();
        super.onDestroy();
    }
}