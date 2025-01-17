package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.PostDTO;
import com.josemeurer.springBoot_mongoDB.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPostController {

    private final PostService postService;

    public UserPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("users/{id}/posts")
    public ResponseEntity<List<PostDTO>> findPostsByUser(@PathVariable String id) {
        List<PostDTO> list = postService.findPostsByUser(id);
        return ResponseEntity.ok().body(list);
    }
}
