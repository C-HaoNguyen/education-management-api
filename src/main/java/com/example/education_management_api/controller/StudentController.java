package com.example.education_management_api.controller;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
            studentService.addStudent(studentName, email, birthday, phoneNumber);
            return ResponseEntity.ok("Add student successfully");
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
