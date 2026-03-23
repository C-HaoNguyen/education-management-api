package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Students;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testCountStudentsByName() {
        //1. Prepare data test
        Students student1 = new Students("Stu1", "stu1@gmail.com", LocalDate.of(1996, 1, 1), "123123213", 1 );
        studentRepository.save(student1);
        Students student2 = new Students("Stu1", "stu1@gmail.com", LocalDate.of(1996, 1, 1), "123123213", 1 );
        studentRepository.save(student2);
        Students student3 = new Students("Stu1", "stu1@gmail.com", LocalDate.of(1996, 1, 1), "123123213", 1 );
        studentRepository.save(student3);

        //2. Call function
        Integer result = studentRepository.countStudentsByName("Stu1");

        //3. Check
        Assertions.assertEquals(3, result);
    }
}
