package com.example.education_management_api.controller;


import com.example.education_management_api.entity.Classes;
import com.example.education_management_api.repository.ClassRepository;
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
}
