package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    public StudentController (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    public List<Students> getAllStudents() {
        List<Students> allStudents = studentRepository.findAll();
        return allStudents;
    }

    @GetMapping("/student-count")
    public Integer getStudentCount() {
        List<Students> allStudents = getAllStudents();
        return allStudents.size();
    }

    @GetMapping("/student-detail")
    public Students getStudentDetail(@RequestParam String name) {
        List<Students> allStudent = getAllStudents();
        for (int i = 0; i < allStudent.size(); i++) {
            Students student = allStudent.get(i);
            if (student.getStudentName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    @PostMapping("/add")
    @ResponseBody
    public void addStudent(@RequestParam String studentName, @RequestParam String email, @RequestParam LocalDate birthDate,
                           @RequestParam String phone) {
        Students student = new Students(studentName, email, birthDate, phone);
        Students savedStudent = studentRepository.save(student);
        System.out.println(savedStudent.getStudentId());
    }

    @PostMapping("/add-full-info")
    @ResponseBody
    public void addFullInfo(@RequestBody Students student) {
        studentRepository.save(student);
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestParam Integer id) {
        studentRepository.deleteById(id);
    }
}
