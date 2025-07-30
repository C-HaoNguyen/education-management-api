package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teachers {
    @Id
    private int teacherId;
    private String teacherName;
    private String email;
    private String phoneNumber;

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
