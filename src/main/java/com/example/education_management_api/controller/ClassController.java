package com.example.education_management_api.controller;


import com.example.education_management_api.dto.ClassDetailDto;
import com.example.education_management_api.entity.Classes;
import com.example.education_management_api.entity.StudentsOfClass;
import com.example.education_management_api.entity.StudentsOfClassId;
import com.example.education_management_api.repository.ClassRepository;
import com.example.education_management_api.repository.StudentsOfClassRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassRepository classRepository;
    private final StudentsOfClassRepository studentsOfClassRepository;

    public ClassController(ClassRepository classRepository, StudentsOfClassRepository studentsOfClassRepository) {
        this.classRepository = classRepository;
        this.studentsOfClassRepository = studentsOfClassRepository;
    }

    @GetMapping("/all")
    public java.util.List<Classes> getAllClasses() {
        // select * from classes
        return classRepository.findAll();
    }

    @GetMapping("/allDetail")
    public List<ClassDetailDto> findAllClassDetail() {
        List<Object[]> results = classRepository.findAllClassesDetail();
        List<ClassDetailDto> details = new ArrayList<>();

        for (Object[] row: results) {
            System.out.println(row[0]);
            //1 -> Object[] [-1, "Java Core", 2, "Dao", "F6", 2025-12-12]
            ClassDetailDto classDetail = new ClassDetailDto(
                    (Integer) row[0],    // class_id
                    (String) row[1],     // class_name
                    (Integer) row[2],    // teacher_id
                    (String) row[3],     // teacher_name
                    (Integer) row[4],    // course_id
                    (String) row[5],     // description (now courseName in DTO)
                    ((Date) row[6]).toLocalDate()
            );
            details.add(classDetail);
        }
        return details;
    }

    @PostMapping("/allDetailBySearch")
    public List<ClassDetailDto> findAllClassDetailBySearch(@RequestParam String className,
                                                           @RequestParam String teacherName,
                                                           @RequestParam String courseName,
                                                           @RequestParam String startDate) {
        LocalDate startDateValue = null;
        if (startDate != null && !startDate.isEmpty()) {
            startDateValue = LocalDate.parse(startDate);
        }
        List<Object[]> results = classRepository.findClassesDetailsByFilterValues(className, teacherName, courseName, startDateValue);

        List<ClassDetailDto> details = new ArrayList<>();

        for (Object[] row : results) {
            System.out.println(row[0]);
            //1 -> Object[] [-1, "Java Core", 2, "Dao", "F6", 2025-12-12]
            ClassDetailDto classDetail = new ClassDetailDto(
                    (Integer) row[0],    // class_id
                    (String) row[1],     // class_name
                    (Integer) row[2],    // teacher_id
                    (String) row[3],     // teacher_name
                    (Integer) row[4],    // course_id
                    (String) row[5],     // description (now courseName in DTO)
                    ((Date) row[6]).toLocalDate()
            );
            details.add(classDetail);
        }
        return details;
    }

    @PostMapping("/add")
    public String createClasses(@RequestParam String className, @RequestParam int teacherId, @RequestParam int courseId, @RequestParam LocalDate startDate) {
        Classes classes = new Classes(className, teacherId, courseId, startDate);
        try {
            classRepository.save(classes);
            return "Đã tạo class thành công!";
        } catch (Exception e) {
            return "Không thể tạo class được!";
        }
    }

    @PutMapping("/update")
    public void updateStudent(@RequestParam Integer classId, @RequestParam String className, @RequestParam Integer teacherId,
                              @RequestParam Integer courseId, @RequestParam LocalDate startDate) {
        Classes updatedClasses = new Classes(classId, className, teacherId, courseId, startDate);
        classRepository.save(updatedClasses);
    }

    @DeleteMapping("/delete")
    public String deleteClass(@RequestParam int classId) {
        try {
            classRepository.deleteById(classId);
            return "Đã xóa class thành công!";
        } catch (Exception e) {
            return "Không thể xóa class được!";
        }
    }

    @PostMapping("/add-student-to-class")
    public String addStudentToClass(@RequestParam Integer classId, @RequestParam Integer studentId) {
        StudentsOfClassId id = new StudentsOfClassId(classId, studentId);
        StudentsOfClass student = new StudentsOfClass(id);
        try {
            boolean isExistedStudents = studentsOfClassRepository.findById(id).isPresent();
            if (isExistedStudents) {
                return "Học viên đã tồn tại trong lớp!";
            }
            studentsOfClassRepository.save(student);
            return "Đã thêm học viên thành công!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Không thể thêm học viên!";
        }
    }

    @DeleteMapping("/remove-student-from-class")
    public String deleteStudentFromClass(@RequestParam Integer classId, @RequestParam Integer studentId) {
        try {
            StudentsOfClassId id = new StudentsOfClassId(classId, studentId);
            studentsOfClassRepository.deleteById(id);
            return "Đã xóa học viên thành công!";
        } catch (Exception e) {
            return "Không thể xóa học viên!";
        }
    }
}
