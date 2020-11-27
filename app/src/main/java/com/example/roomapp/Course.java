package com.example.roomapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Courses")
class Course implements Serializable {

    @PrimaryKey
    private int courseId;

    private String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public static Course[] populateData() {
        return new Course[] {
                new Course(1, "Design Patterns"),
                new Course(2, "Data Inference"),
                new Course(3, "First Year Seminar"),
        };
    }
}