package com.example.education_management_api.dto;

import java.time.LocalDate;

//dto: data transfer object
public class ClassDetailDto {
    private final int classId;
    private final String className;
    private final int teacherId;
    private final String teacherName;
    private final int courseId;
    private final String courseName;
    private final LocalDate startDate;
    private final Long amountOfStudents;

    public ClassDetailDto(final int classId, final String className, final int teacherId,
                          final String teacherName, final int courseId, final String courseName,
                          final LocalDate startDate, final Long amountOfStudents) {
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.amountOfStudents = amountOfStudents;
    }

    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Long getAmountOfStudents() {
        return amountOfStudents;
    }
}
