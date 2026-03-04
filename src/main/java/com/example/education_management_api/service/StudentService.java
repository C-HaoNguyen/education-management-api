package com.example.education_management_api.service;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.ClassRepository;
import com.example.education_management_api.repository.StudentRepository;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Students> findAllStudents(){
        // do filter, do calculation......
        return studentRepository.findAll();
    }
}
