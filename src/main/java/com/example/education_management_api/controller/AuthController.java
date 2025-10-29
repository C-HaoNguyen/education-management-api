package com.example.education_management_api.controller;

import com.example.education_management_api.JwtTokenUtil;
import com.example.education_management_api.entity.Users;
import com.example.education_management_api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;

    public AuthController (UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity register(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        Users existedUser = userRepository.findByUsername(username);
        if (existedUser == null) {
            String encodedPassword = passwordEncoder.encode(password);
            Users user = new Users(username, encodedPassword, role);
            Users savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body("Username already existed!");
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        Users existedUser = userRepository.findByUsername(username);
        if (existedUser == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User hasn't existed.");
        } else {
            boolean isPasswordMatch = passwordEncoder.matches(password, existedUser.getPassword());
            if (isPasswordMatch) {
                String accessToken = jwtTokenUtil.generateToken(username);
                return ResponseEntity
                        .ok()
                        .body(accessToken);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body("Username or password is incorrect.");
            }
        }
    }
}
