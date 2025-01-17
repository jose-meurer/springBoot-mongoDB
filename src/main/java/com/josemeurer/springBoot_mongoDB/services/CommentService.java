package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.entities.Comment;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.services.exceptions.ObjectNotFoundException;
import com.josemeurer.springBoot_mongoDB.repositories.CommentRepository;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentDTO> findCommentsByPost(String id) {
        Post post = findPostById(id);
        List<Comment> list = post.getComments();
        return list.stream().map(CommentDTO::new).toList();
    }

    private Post findPostById(String postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
    }
}
