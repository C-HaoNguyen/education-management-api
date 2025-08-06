package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses, Integer> {
    // JpaRepository already provides basic CRUD operations
    // Additional custom queries can be defined here if needed
}
