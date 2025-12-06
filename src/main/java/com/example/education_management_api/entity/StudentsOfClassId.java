package com.example.education_management_api.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StudentsOfClassId implements Serializable {
    private Integer classId;
    private Integer studentId;

    public StudentsOfClassId() {

    }

    public StudentsOfClassId(Integer classId, Integer studentId) {
        this.classId = classId;
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}

