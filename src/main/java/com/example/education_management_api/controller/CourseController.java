package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Courses;
import com.example.education_management_api.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public List<Courses> getAllCourses() {
        return courseService.findAllCourses();
    }

    @PostMapping("/course-details")
    public ResponseEntity<Courses> findCourseById(@RequestParam int id) {
        Courses course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCourse(@RequestParam int id) {
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public void createCourse(@RequestParam String courseDescription, @RequestParam int duration, @RequestParam String details) {
        courseService.addCourse(courseDescription, duration, details);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestParam int id, @RequestParam String courseDescription, @RequestParam int duration, @RequestParam String details) {
        courseService.updateCourse(id, courseDescription, duration, details);
    }
}
