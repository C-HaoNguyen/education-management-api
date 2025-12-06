package com.example.education_management_api.repository;

import com.example.education_management_api.entity.StudentsOfClass;
import com.example.education_management_api.entity.StudentsOfClassId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsOfClassRepository extends JpaRepository<StudentsOfClass, StudentsOfClassId> {

}
