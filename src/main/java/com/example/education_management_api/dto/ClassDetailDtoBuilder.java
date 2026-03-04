package com.example.education_management_api.dto;

import java.time.LocalDate;

public final class ClassDetailDtoBuilder {
    private int classId;
    private String className;
    private int teacherId;
    private String teacherName;
    private int courseId;
    private String courseName;
    private LocalDate startDate;
    private Long amountOfStudents;

    private ClassDetailDtoBuilder() {
    }

    public static ClassDetailDtoBuilder aClassDetailDto() {
        return new ClassDetailDtoBuilder();
    }

    public ClassDetailDtoBuilder withClassId(int classId) {
        this.classId = classId;
        return this;
    }

    public ClassDetailDtoBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    public ClassDetailDtoBuilder withTeacherId(int teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public ClassDetailDtoBuilder withTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public ClassDetailDtoBuilder withCourseId(int courseId) {
        this.courseId = courseId;
        return this;
    }

    public ClassDetailDtoBuilder withCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public ClassDetailDtoBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public ClassDetailDtoBuilder withAmountOfStudents(Long amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
        return this;
    }

    public ClassDetailDto build() {
        return new ClassDetailDto(classId, className, teacherId, teacherName, courseId, courseName, startDate, amountOfStudents);
    }
}
