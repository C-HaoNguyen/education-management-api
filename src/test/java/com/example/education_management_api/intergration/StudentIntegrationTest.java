package com.example.education_management_api.intergration;

import com.example.education_management_api.entity.Students;
import com.example.education_management_api.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@Import(TestSecurityConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testAddStudentAPI_AddSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                .param("studentName", "Anh")
                .param("email", "anh@gmail.com")
                .param("birthday", "2000-01-01")
                .param("phoneNumber", "0123456789"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // verify DB
        List<Students> students = studentRepository.findAll();
        Assertions.assertEquals(1, students.size());
        Assertions.assertEquals("Anh", students.get(0).getStudentName());
    }

    @Test
    void testAddStudentAPI_AddFailedBecauseStudentAlreadyExist() throws Exception {
        Students student1 = new Students("Anh", "stu1@gmail.com", LocalDate.of(1996, 1, 1), "123123213", 1 );
        studentRepository.save(student1);

        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                        .param("studentName", "Anh")
                        .param("email", "anh@gmail.com")
                        .param("birthday", "2000-01-01")
                        .param("phoneNumber", "0123456789"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Students already exist"));
    }
}