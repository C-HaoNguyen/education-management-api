package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Classes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassRepository extends JpaRepository<Classes, Integer> {
    // This interface will automatically provide CRUD operations for Classes entity
    // Additional custom query methods can be defined here if needed

    @Query(
            value = "SELECT c.class_id, c.class_name, t.teacher_id, t.teacher_name, co.course_id, co.description, c.start_date" +
                    " FROM classes c " +
                    "JOIN teachers t ON c.teacher_id = t.teacher_id " + // 'Teacher' is the JPA entity name
                    "JOIN courses co ON c.course_id = co.course_id", nativeQuery = true    // 'Course' is the JPA entity name
    )
    List<Object[]> findAllClassesDetail();

    @Query(
            value = "SELECT c.class_id, c.class_name, t.teacher_id, t.teacher_name, co.course_id, co.description, c.start_date" +
                    " FROM classes c " +
                    "JOIN teachers t ON c.teacher_id = t.teacher_id " + // 'Teacher' is the JPA entity name
                    "JOIN courses co ON c.course_id = co.course_id" +
                    " WHERE (:className IS NULL OR c.class_name LIKE CONCAT('%', :className, '%')) " +
                    "  AND (:teacherName IS NULL OR t.teacher_name LIKE CONCAT('%', :teacherName, '%')) " +
                    "  AND (:courseDescription IS NULL OR co.description LIKE CONCAT('%', :courseDescription, '%'))",
            nativeQuery = true    // 'Course' is the JPA entity name
    )
    // where c.class_name LIKE '%Java%';
    List<Object[]> findClassesDetailsByFilterValues(@Param("className") String className,
                                                    @Param("teacherName") String teacherName,
                                                    @Param("courseDescription") String courseDescription);
}
