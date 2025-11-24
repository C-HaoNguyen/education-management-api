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
    public int duration;
    public String details;

    public int getCourseId() {
        return courseId;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Courses() {
    }

    public Courses(String courseDescription, int duration, String details) {
        this.description = courseDescription;
        this.duration = duration;
        this.details = details;
    }

    public Courses(int id, String description, int duration, String details) {
        this.courseId = id;
        this.description = description;
        this.duration = duration;
        this.details = details;
    }
}
