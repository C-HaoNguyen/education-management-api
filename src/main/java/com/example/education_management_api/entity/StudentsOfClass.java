package com.example.education_management_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studentsofclass")
public class StudentsOfClass {
    @Id
    private StudentsOfClassId id;

    public StudentsOfClass() {
    }

    public StudentsOfClass(StudentsOfClassId id) {
        this.id = id;
    }

    public StudentsOfClassId getId() {
        return id;
    }

    public void setId(StudentsOfClassId id) {
        this.id = id;
    }
}
