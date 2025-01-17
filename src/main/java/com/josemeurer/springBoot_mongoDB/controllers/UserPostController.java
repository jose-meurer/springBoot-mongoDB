package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.PostDTO;
import com.josemeurer.springBoot_mongoDB.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPostController {

    private final UserService userService;

    public UserPostController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}/posts")
    public ResponseEntity<List<PostDTO>> findPostsByUser(@PathVariable String id) {
        List<PostDTO> list = userService.findPosts(id);
        return ResponseEntity.ok().body(list);
    }
}
