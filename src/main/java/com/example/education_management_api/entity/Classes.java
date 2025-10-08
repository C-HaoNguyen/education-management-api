package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private int classId;
    private String className;
    private int teacherId;
    private int courseId;
    private LocalDate startDate;

    public Classes(final Integer classId, final String className, final Integer teacherId, final Integer courseId, final LocalDate startDate) {
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.startDate = startDate;
    }

    public String getClassName() {
        return className;
    }

    public int getClassId() {
        return classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Classes() {
    }
    public Classes(int teacherId, int courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }
}
