package com.example.roomapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Course.class, Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDB;
    public abstract CoursesDao coursesDao();
    public abstract StudentDao studentsDao();
    public synchronized static AppDatabase getInstance(Context context) {
        if (appDB == null) {
            appDB = buildDatabase(context);
        }
        return appDB;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "my-data").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).coursesDao().insertAllCourses(Course.populateData());
                        getInstance(context).studentsDao().insertAllStudents(Student.populateData());
                    }
                });
            }
        }).build();
    }

    public void cleanUp(){
        appDB = null;
    }

}


