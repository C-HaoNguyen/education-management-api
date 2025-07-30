package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
//@Table(name = "STUDENT")
public class Students {
    @Id
    private int studentId;
    private String studentName;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
