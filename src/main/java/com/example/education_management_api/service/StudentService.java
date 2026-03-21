package com.example.education_management_api.service;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> findAllStudents() {
        return studentRepository.findAll();
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

    public String validateNewStudent(String studentName, String email, LocalDate birthday, String phoneNumber) {
        //Kiểm tra student name valid
        Integer countExistStudentSameName = studentRepository.countStudentsByName(studentName);
        if (countExistStudentSameName > 0) {
            return "Students already exist";
        }

        if (!email.contains("@")) {
            return "Invalid email";
        }

        if (!birthday.isBefore(LocalDate.now())) {
            return "Invalid birthday";
        }

        if (phoneNumber.length() != 10) {
            return "Invalid phone number";
        }
        return null;
    }

    // Hao
    public void addStudent(String studentName, String email, LocalDate birthday, String phoneNumber) {
        Students student = new Students(studentName, email, birthday, phoneNumber, 1);
        studentRepository.save(student);
    }
    
    public void updateStudent(Integer id, String studentName, String email, LocalDate birthday, String phoneNumber) {
        Students student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setStudentName(studentName);
            student.setEmail(email);
            student.setBirthday(birthday);
            student.setPhoneNumber(phoneNumber);
            studentRepository.save(student);
        }
    }

    public void updateStudentStatus(Integer id) {
        Students student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setIsActive(student.getIsActive() == 1 ? 0 : 1);
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Integer id) {
        Students student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setIsActive(0);
            studentRepository.save(student);
        }
    }
}
