package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    private String teacherName;
    private String email;
    private String phoneNumber;

    // Default constructor for JPA
    public Teachers() {
    }

    public Teachers(String teacherName, String email, String phoneNumber) {
        this.teacherName = teacherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Teachers(int teacherId, String teacherName, String email, String phoneNumber) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
