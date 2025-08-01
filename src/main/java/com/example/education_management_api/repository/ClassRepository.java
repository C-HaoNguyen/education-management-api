package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Classes, Integer> {
    // This interface will automatically provide CRUD operations for Classes entity
    // Additional custom query methods can be defined here if needed
}
