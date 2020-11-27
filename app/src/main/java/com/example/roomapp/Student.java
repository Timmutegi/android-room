package com.example.roomapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Students")
public class Student implements Serializable {
    @PrimaryKey
    private int StudentId;

    private int CourseID;

    private int Q1;

    private int Q2;

    private int Q3;

    private int Q4;

    private int Q5;

    public Student(int StudentId, int CourseID, int Q1, int Q2, int Q3, int Q4, int Q5) {
        this.StudentId = StudentId;
        this.CourseID = CourseID;
        this.Q1 = Q1;
        this.Q2 = Q2;
        this.Q3 = Q3;
        this.Q4 = Q4;
        this.Q5 = Q5;
    }

    public int getStudentId() {
        return StudentId;
    }

    public int getCourseID() {
        return CourseID;
    }

    public int getQ1() {
        return Q1;
    }

    public int getQ2() {
        return Q2;
    }

    public int getQ3() { return Q3; }

    public int getQ4() {
        return Q4;
    }

    public int getQ5() {
        return Q5;
    }

    public static Student[] populateData() {
        return new Student[] {
                new Student(1111, 1, 60, 68, 70, 80, 90),
                new Student(1112, 1, 60, 68, 70, 80, 90),
                new Student(1113, 1, 60, 68, 70, 80, 90),
        };
    }
}
