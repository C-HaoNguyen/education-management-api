package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String description;

    public int getCourseId() {
        return courseId;
    }

    public String getDescription() {
        return description;
    }

    public Courses() {
    }
    public Courses(String courseDescription) {
        this.description = courseDescription;
    }

    public Courses(int id, String description) {
        this.courseId = id;
        this.description = description;
    }
}
