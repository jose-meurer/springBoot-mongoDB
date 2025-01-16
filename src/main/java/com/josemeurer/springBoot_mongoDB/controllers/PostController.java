package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.dtos.PostDTO;
import com.josemeurer.springBoot_mongoDB.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        PostDTO postDTO = postService.findById(id);
        return ResponseEntity.ok().body(postDTO);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> findComments(@PathVariable String id) {
        List<CommentDTO> list = postService.findComments(id);
        return ResponseEntity.ok().body(list);
    }
}
