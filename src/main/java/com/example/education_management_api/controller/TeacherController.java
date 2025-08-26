package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Teachers;
import com.example.education_management_api.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/all")
    public List<Teachers> getAllTeachers() {
        List<Teachers> allTeachers = teacherRepository.findAll();
        return allTeachers;
    }

    @PostMapping("/add")
    public void createTeacher(@RequestParam String teacherName, @RequestParam String email, @RequestParam String phoneNumber) {
        Teachers newTeacher = new Teachers(teacherName, email, phoneNumber);
        Teachers savedTeacher = teacherRepository.save(newTeacher);
        System.out.println("Created new teacher: " + savedTeacher.getTeacherName());
    }

    @GetMapping("/find-teacher-by-id")
    public Teachers getTeacherById(@RequestParam int id) {
        return teacherRepository.findById(id).isPresent() ? teacherRepository.findById(id).get() : null;
    }

    @PutMapping("/update")
    public void updateTeacher(@RequestParam int teacherId, @RequestParam String teacherName, @RequestParam String email, @RequestParam String phoneNumber) {
        Teachers newTeacher = new Teachers(teacherId, teacherName, email, phoneNumber);
        Teachers savedTeacher = teacherRepository.save(newTeacher);
        System.out.println("Created new teacher: " + savedTeacher.getTeacherName());
    }

    @DeleteMapping("/delete")
    public void deleteTeacher(@RequestParam int id) {
        teacherRepository.deleteById(id);
    }

}