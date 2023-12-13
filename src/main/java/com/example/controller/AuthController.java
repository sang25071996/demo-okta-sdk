package com.example.controller;

import com.example.model.Users;
import com.example.request.UserRequest;
import com.example.service.AuthService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<Users> getUser(@PathVariable String userId) {
        return ResponseEntity.ok().body(authService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<Users>> getUsers(@RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(authService.getUsers(pageSize));
    }

    @PostMapping
    public ResponseEntity<Users> creatUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(authService.createUser(userRequest));
    }

    @PutMapping("{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(authService.updateUser(userId, userRequest));
    }
}
