package com.example.roomapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoursesDao {
    @Insert
    void insertAllCourses(Course... courses);

    @Query("SELECT * FROM Courses")
    List<Course> getAll();
}
