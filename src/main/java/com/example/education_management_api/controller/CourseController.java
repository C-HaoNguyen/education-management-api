package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Courses;
import com.example.education_management_api.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping("/course-details")
    public ResponseEntity<Courses> getCourseById(@RequestParam int id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCourse(@RequestParam int id) {
        try {
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public void createCourse(@RequestParam String courseDescription) {
        Courses courses = new Courses(courseDescription);
        courseRepository.save(courses);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestParam int id, @RequestParam String courseDescription) {
        Courses courses = new Courses(id, courseDescription);
        courseRepository.save(courses);
    }
}
