package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.dtos.CommentUpdateDTO;
import com.josemeurer.springBoot_mongoDB.entities.Comment;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.repositories.CommentRepository;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import com.josemeurer.springBoot_mongoDB.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDTO findById(String id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment =optionalComment.orElseThrow(() -> new ObjectNotFoundException("Comment not found"));
        return new CommentDTO(comment);
    }

    public List<CommentDTO> findAll() {
        List<Comment> list = commentRepository.findAll();
        return list.stream().map(CommentDTO::new).toList();
    }

    public List<CommentDTO> findCommentsByPost(String id) {
        Post post = findPostById(id);
        List<Comment> list = post.getComments();
        return list.stream().map(CommentDTO::new).toList();
    }

    private Post findPostById(String postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
    }

    public void delete(String id) {
        if(!commentRepository.existsById(id)) {
            throw new ObjectNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

    public CommentDTO update(String id, CommentUpdateDTO dto) {
        Comment comment = updateDataComment(id, dto);
        return new CommentDTO(comment);
    }

    private Comment updateDataComment(String id, CommentUpdateDTO dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        comment.setBody(dto.getBody());
        commentRepository.save(comment);
        return comment;
    }
}
