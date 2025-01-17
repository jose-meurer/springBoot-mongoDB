package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostCommentController {

    private final CommentService commentService;

    public PostCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentDTO>> findCommentsByPost(@PathVariable String id) {
        List<CommentDTO> list = commentService.findCommentsByPost(id);
        return ResponseEntity.ok().body(list);
    }
}
