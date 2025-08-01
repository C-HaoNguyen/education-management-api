package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Courses {
    @Id
    private int courseId;
    private String courseDescription;
}
