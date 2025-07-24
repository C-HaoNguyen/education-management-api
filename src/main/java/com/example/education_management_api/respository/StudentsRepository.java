package com.example.education_management_api.respository;

import com.example.education_management_api.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {
}
