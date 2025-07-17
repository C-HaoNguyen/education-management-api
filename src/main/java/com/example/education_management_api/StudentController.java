package com.example.education_management_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/students")
public class StudentController {

    @GetMapping("/all")
    public String getAllStudents() {
        return "List of all students";
    }

    @GetMapping("/student-count")
    public String getStudentCount() {
        return "Total number of students";
    }

    @GetMapping("/student-details")
    public String getStudentDetails() {
        return "Ho ten: Nguyen Tan Cao Hao - Dia chi: Da Nang";
    }
}
