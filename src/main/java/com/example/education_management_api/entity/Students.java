package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private double score;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;

    public void setStudentId(final int studentId) {
        this.studentId = studentId;
    }

    public void setScore(final double score) {
        this.score = score;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getScore() {
        return score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }
}
