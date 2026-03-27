package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.service.StudentService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Students> getAllStudents() {
        return studentService.findAllStudents();
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
    public ResponseEntity<?> addStudent(@RequestParam String studentName, @RequestParam String email, @RequestParam LocalDate birthday, @RequestParam String phoneNumber) {
        String errorMessage = studentService.validateNewStudent(studentName, email, birthday, phoneNumber);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            try {
                studentService.addStudent(studentName, email, birthday, phoneNumber);
                return ResponseEntity.ok("Add student successfully");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("Cannot add this student because of duplicate username");
            }
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestParam Integer studentId, @RequestParam String studentName, @RequestParam String email, @RequestParam LocalDate birthday, @RequestParam String phoneNumber) {
        String errorMessage = studentService.validateUpdateStudent(studentId, studentName, email, birthday, phoneNumber);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            studentService.updateStudent(studentId, studentName, email, birthday, phoneNumber);
            return ResponseEntity.ok("Update student successfully");
        }
    }

    @PutMapping("/update-status")
    public void updateStudentStatus(@RequestParam Integer studentId) {
        studentService.updateStudentStatus(studentId);
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestParam Integer id) {
        studentService.deleteStudent(id);
    }
}
