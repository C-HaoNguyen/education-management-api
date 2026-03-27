package com.example.education_management_api.service;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.StudentRepository;
import com.example.education_management_api.repository.UserRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder,
                   UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
//        Integer countExistStudentSameName = studentRepository.countStudentsByName(studentName);
//        if (countExistStudentSameName > 0) {
//            return "Students already exist";
//        }

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

    public String validateUpdateStudent(Integer studentId, String updatedStudentName, String email, LocalDate birthday, String phoneNumber) {
        //Kiểm tra student name valid
        Students existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null && !existingStudent.getStudentName().equals(updatedStudentName) ) {
            Integer countExistStudentSameName = studentRepository.countStudentsByName(updatedStudentName);
            if (countExistStudentSameName > 0) {
                return "Students already exist";
            }
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
    @Transactional
    public void addStudent(String studentName, String email, LocalDate birthday, String phoneNumber) throws DataIntegrityViolationException {
        // create student
        Students student = new Students(studentName, email, birthday, phoneNumber, 1);
        studentRepository.save(student);

        // create user login cho new student
        String encodedPassword = passwordEncoder.encode("123456");
        userRepository.insertUser(studentName, encodedPassword, "student");
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

    @Transactional
    public void updateStudent(List<Students> newStudents) {
        for (Students students : newStudents) {
            studentRepository.save(students);
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
