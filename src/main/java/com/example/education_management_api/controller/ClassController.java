package com.example.education_management_api.controller;


import com.example.education_management_api.entity.Classes;
import com.example.education_management_api.repository.ClassRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassRepository classRepository;

    public ClassController(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @GetMapping("/all")
    public java.util.List<Classes> getAllClasses() {
        return classRepository.findAll();
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
