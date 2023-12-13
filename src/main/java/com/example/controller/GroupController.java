package com.example.controller;

import com.example.model.Group;
import com.example.service.AuthService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groups")
public class GroupController {

    private final AuthService authService;

    public GroupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public ResponseEntity<Group> creatUser(@RequestBody Group group) {
        return ResponseEntity.ok().body(authService.createGroup(group));
    }
    @GetMapping()
    public ResponseEntity<List<Group>> getGroups() {
        return ResponseEntity.ok().body(authService.getGroups());
    }
}
