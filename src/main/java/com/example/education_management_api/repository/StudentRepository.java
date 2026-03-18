package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Students, Integer> {
    // This interface will automatically provide CRUD operations for Students entity
    // Additional custom query methods can be defined here if needed

   @Query(value = "select count(*) from students where students.student_name = :studentName",
   nativeQuery = true)
   Integer countStudentsByName(@Param("studentName" ) String studentName);
}
