package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teachers,  Integer> {
    // This interface will automatically provide CRUD operations for Teachers entity
    // Additional custom query methods can be defined here if needed
}
