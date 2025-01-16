package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.dtos.PostDTO;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.exceptions.ObjectNotFoundException;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDTO findById(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        return new PostDTO(post);
    }

    public List<CommentDTO> findComments(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        return post.getComments().stream().map(CommentDTO::new).toList();
    }
}
