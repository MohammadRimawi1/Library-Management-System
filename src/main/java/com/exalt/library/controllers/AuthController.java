package com.exalt.library.controllers;

import com.exalt.library.dto.LoginDTO;
import com.exalt.library.dto.RegisterDTO;
import com.exalt.library.models.users.User;
import com.exalt.library.services.UserServices;
import com.exalt.library.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * a controller handling registration and login
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServices userServices; // defines user services

    /**
     * constructor injection
     * @param userServices
     */
    public AuthController(UserServices userServices) {
        this.userServices = userServices;
    }

    /**
     * a method for registering for your account
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterDTO request) {
        User user = userServices.register(request);
        return ResponseEntity.status(201).body(ApiResponse.success(201, Map.of("email", user.getEmail(), "role", user.getRole())));
    }

    /**
     * a method for logging in ur account
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO request) {
        String token = userServices.login(request);
        return ResponseEntity.ok(ApiResponse.success(200, Map.of("token", token)));
    }
}