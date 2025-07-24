package com.example.education_management_api.controller;

import com.example.education_management_api.model.Student;
import com.example.education_management_api.entity.Students;
import com.example.education_management_api.respository.StudentsRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentsRepository studentsRepository;

    StudentController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/all")
    public List<Students> getAllStudents() {
        final List<Students> all = studentsRepository.findAll();
        System.out.println("Tat ca sinh vien co trong data base la gi:" + all.size());
        return all;
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
        Students student = new Students();
        student.setFirstName(name);
        student.setScore(score);
        studentsRepository.save(student);
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

    //Lương của giáo viên là
}
