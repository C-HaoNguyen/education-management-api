package com.example.education_management_api.controller;


import com.example.education_management_api.dto.ClassDetailDto;
import com.example.education_management_api.entity.Classes;
import com.example.education_management_api.repository.ClassRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
                                                           @RequestParam String courseName) {
        List<Object[]> results = classRepository.findClassesDetailsByFilterValues(className, teacherName, courseName);

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
}
