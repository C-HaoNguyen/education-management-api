package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private int classId;
    private int teacherId;
    private int courseId;

    public Classes() {
    }
    public Classes(int teacherId, int courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }
}
