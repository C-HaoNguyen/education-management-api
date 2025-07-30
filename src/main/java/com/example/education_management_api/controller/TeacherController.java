package com.example.education_management_api.controller;

import com.example.education_management_api.model.Teacher;
import com.example.education_management_api.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = getFakeTeacherList();
        return teachers;
    }

    private List<Teacher> getFakeTeacherList() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher1 = new Teacher(1, "Nguyen", "Van A", LocalDate.of(1985, 5, 20), "Hanoi");
        teachers.add(teacher1);
        Teacher teacher2 = new Teacher(2, "Tran", "Thi B", LocalDate.of(1990, 3, 15), "Ho Chi Minh City");
        teachers.add(teacher2);
        Teacher teacher3 = new Teacher(3, "Le", "Van C", LocalDate.of(1988, 7, 10), "Da Nang");
        teachers.add(teacher3);
        return teachers;
    }

    @PostMapping("/create-teacher")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        List<Teacher> teachers = getAllTeachers();
        teachers.add(teacher);
        System.out.println("Tổng số lượng teacher hiện tại: " + teachers.size());
        return teacher;
    }

    @GetMapping("/find-teachers-by-first-name")
    public List<Teacher> getTeachersByFirstName(@RequestParam String firstName) {
        List<Teacher> teachers = getFakeTeacherList();
        List<Teacher> result = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getFirstName().equals(firstName)) {
                result.add(teachers.get(i));
            }
        }
        return result;
    }


    @PutMapping
    public void updateSalary(@RequestParam int id, @RequestParam int newSalary) {
        List<Teacher> teachers = getFakeTeacherList();
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.get(i).setSalary(newSalary);
                System.out.println("Updated salary for teacher with ID " + id + " to " + newSalary);
                return;
            }
        }
    }


}