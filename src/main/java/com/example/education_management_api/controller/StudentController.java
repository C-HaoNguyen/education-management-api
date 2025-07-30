package com.example.education_management_api.controller;

import com.example.education_management_api.model.Student;
import com.example.education_management_api.entity.Students;
import org.springframework.web.bind.annotation.*;
import com.example.education_management_api.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private List<Student> getFakeStudentList() {
        List<Student> students = new ArrayList<>();
        Student student = new Student(1, "Dao1", "Cao", LocalDate.of(1996, 01, 04), "Quang Nam");
        students.add(student);
        Student student1 = new Student(2, "Dao2", "Cao", LocalDate.of(1996, 01, 04), "Quang Nam");
        students.add(student1);
        Student student2 = new Student(3, "Dao3", "Cao", LocalDate.of(1996, 01, 04), "Quang Nam");
        students.add(student2);
        Student student3 = new Student(4, "Dao4", "Cao", LocalDate.of(1996, 01, 04), "Quang Nam");
        students.add(student3);
        return students;
    }

    @GetMapping("/student-count")
    public Integer getStudentCount() {
        List<Student> allStudent = getFakeStudentList();
        return allStudent.size();
    }

    @GetMapping("/student-detail")
    public Student getStudentDetail() {
        List<Student> allStudent = getFakeStudentList();
        return allStudent.get(0);
    }

    @PostMapping("/add-name")
    @ResponseBody
    public void addStudentName(@RequestParam String name, @RequestParam Integer score) {
        Student student = new Student();
        student.setFirstName(name);
        student.setScore(score);
        List<Student> allStudent = getFakeStudentList();
        allStudent.add(student);
    }

    @PostMapping("/add-address")
    @ResponseBody
    public void addStudentAddress(@RequestBody String body) {
        System.out.println("Ten hoc sinh duoc tao la: " + body);
    }

    @PostMapping("/add-full-info")
    @ResponseBody
    public void addFullInfo(@RequestBody Student student) {
        System.out.println("Thong tin hoc sinh moi:" + student.getFirstName());
    }

    @PutMapping("/update-score")
    @ResponseBody
    public void updateScore(@RequestParam Integer id, @RequestParam Integer score) {
        List<Student> allStudent = getFakeStudentList();
        for (int i = 0; i < allStudent.size(); i++) {
            Student student = allStudent.get(i);
            if (student.getStudentId() == id) {
                student.setScore(score);
                System.out.println("Da update thanh cong score cho sinh vien: " + student.getFirstName());
            }
        }
    }
}
