package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClassRepository extends JpaRepository<Classes, Integer> {
    // This interface will automatically provide CRUD operations for Classes entity
    // Additional custom query methods can be defined here if needed

    @Query(
            value = "SELECT c.class_id, c.class_name, t.teacher_id, t.teacher_name, co.course_id, co.description, c.start_date, COUNT(studentsofclass.student_id)" +
                    " FROM classes c " +
                    "JOIN teachers t ON c.teacher_id = t.teacher_id " + // 'Teacher' is the JPA entity name
                    "JOIN courses co ON c.course_id = co.course_id " +
                    "left join studentsofclass on c.class_id = studentsofclass.class_id " +
                    "group by c.class_id, c.class_name, t.teacher_id, t.teacher_name, co.course_id, co.description, c.start_date", nativeQuery = true    // 'Course' is the JPA entity name
    )
    List<Object[]> findAllClassesDetail();

    @Query(
            value = "SELECT c.class_id, c.class_name, t.teacher_id, t.teacher_name, co.course_id, co.description, c.start_date, COUNT(studentsofclass.student_id)" +
                    " FROM classes c " +
                    "JOIN teachers t ON c.teacher_id = t.teacher_id " + // 'Teacher' is the JPA entity name
                    "JOIN courses co ON c.course_id = co.course_id " +
                    "left join studentsofclass ON c.class_id = studentsofclass.class_id " +
                    " WHERE (:className IS NULL OR c.class_name LIKE CONCAT('%', :className, '%')) " +
                    "  AND (:teacherName IS NULL OR t.teacher_name LIKE CONCAT('%', :teacherName, '%')) " +
                    "  AND (:courseDescription IS NULL OR co.description LIKE CONCAT('%', :courseDescription, '%'))" +
                    "  AND c.start_date = COALESCE(:startDate, c.start_date) " +
                    "GROUP BY c.class_id, c.class_name, t.teacher_id, t.teacher_name, co.course_id, co.description, c.start_date",
            nativeQuery = true    // 'Course' is the JPA entity name
    )
    // where c.class_name LIKE '%Java%';
    List<Object[]> findClassesDetailsByFilterValues(@Param("className") String className,
                                                    @Param("teacherName") String teacherName,
                                                    @Param("courseDescription") String courseDescription,
                                                    @Param("startDate") LocalDate startDate);

    @Query(
            value = "SELECT students.*\n" +
                    "FROM studentsofclass\n" +
                    "JOIN students ON studentsofclass.student_id = students.student_id\n" +
                    "WHERE studentsofclass.class_id = :classIdInput;",
            nativeQuery = true
    )
    List<Object[]> listStudentsOfClass(@Param("classIdInput") Integer classId);
}
