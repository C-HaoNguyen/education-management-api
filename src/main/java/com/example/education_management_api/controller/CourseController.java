package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Courses;
import com.example.education_management_api.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/all")
    public List<Courses> getAllCourses() {
        List<Courses> allCourses = courseRepository.findAll();
        return allCourses;
    }

    @GetMapping("/find")
    public ResponseEntity<Courses> getCourseById(@RequestParam int id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public void deleteCourse(@RequestParam int id) {
        courseRepository.deleteById(id);
    }
}
