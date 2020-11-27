package com.example.roomapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertAllStudents(Student... students);

    @Query("SELECT * FROM Students")
    List<Student> getAll();

}
