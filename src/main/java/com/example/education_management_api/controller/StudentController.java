package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.StudentRepository;
import com.example.education_management_api.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Students> getAllStudents() {
        List<Students> allStudents = studentRepository.findAll();
        return allStudents;
    }

    @GetMapping("/all-active")
    public List<Students> getAllActiveStudents() {
        return studentService.findActiveStudents();
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
    public ResponseEntity addStudent(@RequestParam String studentName, @RequestParam String email, @RequestParam LocalDate birthday, @RequestParam String phoneNumber) {
        return studentService.addStudent(studentName, email, birthday, phoneNumber);
    }

    @PostMapping("/add-full-info")
    @ResponseBody
    public void addFullInfo(@RequestBody Students student) {
        studentRepository.save(student);
    }

    @PutMapping("/update")
    public void updateStudent(@RequestParam Integer studentId, @RequestParam String studentName, @RequestParam String email, @RequestParam LocalDate birthday, @RequestParam String phoneNumber) {
        Students updatedStudent = new Students(studentId, studentName, email, birthday, phoneNumber);
        studentRepository.save(updatedStudent);
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestParam Integer id) {
        studentRepository.deleteById(id);
    }
}
