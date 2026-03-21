package com.example.education_management_api.service;

import com.example.education_management_api.entity.Courses;
import com.example.education_management_api.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    CourseRepository courseRepository;

    CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Courses> findAllCourses() {
        return courseRepository.findAll();
    }

    public Courses getCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(String courseDescription, int duration, String details) {
        Courses courses = new Courses(courseDescription, duration, details);
        courseRepository.save(courses);
    }

    public void updateCourse(int id, String courseDescription, int duration, String details) {
        Courses course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setCourseDescription(courseDescription);
            course.setDuration(duration);
            course.setDetails(details);
            courseRepository.save(course);
        }
    }

    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }
}
