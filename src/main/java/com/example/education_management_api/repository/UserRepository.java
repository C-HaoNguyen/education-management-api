package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
