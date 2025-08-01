package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import org.springframework.cglib.core.Local;

@Entity
//@Table(name = "STUDENT")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private int studentId;
    private String studentName;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;

    public Students(String studentName, String email, LocalDate birthday, String phoneNumber) {
        this.studentName = studentName;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

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
