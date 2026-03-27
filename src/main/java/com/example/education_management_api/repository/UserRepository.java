package com.example.education_management_api.repository;

import com.example.education_management_api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users(username, password, role) VALUES (:username, :password, :role)", nativeQuery = true)
    void insertUser(@Param("username") String username, @Param("password") String password, @Param("role") String role);
}
