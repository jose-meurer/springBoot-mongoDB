package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.dtos.CommentInsertDTO;
import com.josemeurer.springBoot_mongoDB.dtos.CommentUpdateDTO;
import com.josemeurer.springBoot_mongoDB.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable String id) {
        CommentDTO dto = commentService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll() {
        List<CommentDTO> list = commentService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody CommentUpdateDTO dto) {
        CommentDTO commentDto = commentService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> insert(@PathVariable String id, @RequestBody CommentInsertDTO dto) {
        CommentDTO commentDto = commentService.insert(id, dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentDto.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
