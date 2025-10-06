package com.example.education_management_api.controller;


import com.example.education_management_api.dto.ClassDetailDto;
import com.example.education_management_api.entity.Classes;
import com.example.education_management_api.repository.ClassRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassRepository classRepository;

    public ClassController(ClassRepository classRepository) {
        this.classRepository = classRepository;
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
            ClassDetailDto classDetail = new ClassDetailDto(
                    ((Number) row[0]).intValue(),    // class_id
                    (String) row[1],                 // class_name
                    ((Number) row[2]).intValue(),    // teacher_id
                    (String) row[3],                 // teacher_name
                    ((Number) row[4]).intValue(),    // course_id
                    (String) row[5],                 // description (now courseName in DTO)
                    LocalDate.of(2023, 01, 01)
            );
            details.add(classDetail);
        }
        return details;
    }

    @PostMapping("/add")
    public String createClasses(@RequestParam int teacherId, @RequestParam int courseId) {
        Classes classes = new Classes(teacherId, courseId);
        try {
            classRepository.save(classes);
            return "Đã tạo class thành công!";
        } catch (Exception e) {
            return "Không thể tạo class được!";
        }
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
}
