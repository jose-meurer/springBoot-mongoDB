package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostCommentController {

    private final PostService postService;

    public PostCommentController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentDTO>> findCommentsByPost(@PathVariable String id) {
        List<CommentDTO> list = postService.findComments(id);
        return ResponseEntity.ok().body(list);
    }
}
