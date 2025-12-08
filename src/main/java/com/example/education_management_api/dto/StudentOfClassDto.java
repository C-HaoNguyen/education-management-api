package com.example.education_management_api.dto;

import java.time.LocalDate;

public class StudentOfClassDto {
    private Integer studentId;
    private String studentName;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;

    public StudentOfClassDto(Integer studentId, String studentName, String email, LocalDate birthday, String phoneNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public Integer getStudentId() {
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
