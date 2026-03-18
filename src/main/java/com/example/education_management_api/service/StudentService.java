package com.example.education_management_api.service;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.StudentRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> findActiveStudents() {
        // do filter, do calculation.....
        List<Students> allStudents = studentRepository.findAll();
        List<Students> activeStudents = new ArrayList<>();
        for (Students students : allStudents) {
            if (students.getIsActive() == 1) {
                activeStudents.add(students);
            }
        }
        return activeStudents;
    }

    // Hao
    public ResponseEntity addStudent(String studentName, String email, LocalDate birthday, String phoneNumber) {
        //Kiểm tra student name valid
        Integer countExistStudentSameName = studentRepository.countStudentsByName(studentName);
        if (countExistStudentSameName > 0) {
            return ResponseEntity.badRequest().body("Students already exist");
        }

        if (!email.contains("@")) {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        if (!birthday.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Invalid birthday");
        }

        if (phoneNumber.length() != 10) {
            return ResponseEntity.badRequest().body("Invalid phone number");
        }

        Students student = new Students(studentName, email, birthday, phoneNumber, 1);
        studentRepository.save(student);
        return ResponseEntity.ok().build();
    }
}
