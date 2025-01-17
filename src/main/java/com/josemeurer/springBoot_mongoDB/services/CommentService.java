package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.CommentDTO;
import com.josemeurer.springBoot_mongoDB.dtos.CommentInsertDTO;
import com.josemeurer.springBoot_mongoDB.dtos.CommentUpdateDTO;
import com.josemeurer.springBoot_mongoDB.entities.Comment;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.entities.User;
import com.josemeurer.springBoot_mongoDB.entities.UserAuthor;
import com.josemeurer.springBoot_mongoDB.repositories.CommentRepository;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import com.josemeurer.springBoot_mongoDB.repositories.UserRepository;
import com.josemeurer.springBoot_mongoDB.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public void delete(String id) {
        if(!commentRepository.existsById(id)) {
            throw new ObjectNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

    @Transactional
    public CommentDTO update(String id, CommentUpdateDTO dto) {
        Comment comment = updateDataComment(id, dto);
        commentRepository.save(comment);
        return new CommentDTO(comment);
    }

    @Transactional
    public CommentDTO insert(String id, CommentInsertDTO dto) {
        User user = getUserById(dto.getUserId());
        Comment comment = createComment(dto, user);
        comment = commentRepository.save(comment);
        Post post = getPostById(id);
        addCommentToPost(post, comment);
        return new CommentDTO(comment);
    }

    private Comment updateDataComment(String id, CommentUpdateDTO dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        comment.setBody(dto.getBody());
        return comment;
    }

    private Comment createComment(CommentInsertDTO dto, User user) {
        return new Comment(null, Instant.now(), dto.getBody(), new UserAuthor(user.getId(), user.getName()));
    }

    private User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    private Post getPostById(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found"));
    }

    @Transactional
    private void addCommentToPost(Post post, Comment comment) {
        post.getComments().add(comment);
        postRepository.save(post);
    }
}
