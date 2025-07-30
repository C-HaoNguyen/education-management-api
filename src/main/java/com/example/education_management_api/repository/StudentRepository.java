package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer> {
    // This interface will automatically provide CRUD operations for Students entity
    // Additional custom query methods can be defined here if needed
}
