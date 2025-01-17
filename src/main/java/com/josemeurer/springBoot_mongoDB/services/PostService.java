package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.PostDTO;
import com.josemeurer.springBoot_mongoDB.dtos.PostInsertDTO;
import com.josemeurer.springBoot_mongoDB.dtos.PostUpdateDTO;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.entities.User;
import com.josemeurer.springBoot_mongoDB.entities.UserAuthor;
import com.josemeurer.springBoot_mongoDB.exceptions.ObjectNotFoundException;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import com.josemeurer.springBoot_mongoDB.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDTO> findAll() {
        List<Post> list = postRepository.findAll();
        return list.stream().map(PostDTO::new).toList();
    }

    public PostDTO findById(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        return new PostDTO(post);
    }

    @Transactional
    public PostDTO insert(PostInsertDTO dto) {
        User user = findUserById(dto.getUserId());
        Post post = createPost(dto, user);
        savePostAndUser(post, user);
        return new PostDTO(post);
    }

    @Transactional
    public void delete(String id) {
        if(!postRepository.existsById(id)) {
            throw new ObjectNotFoundException("Post not found");
        }
        postRepository.deleteById(id);
    }

    @Transactional
    public PostDTO update(String id, PostUpdateDTO updateDto) {
        Post post = updateDataPost(id, updateDto);
        postRepository.save(post);
        return new PostDTO();
    }

    public List<PostDTO> findPostsByUser (String id) {
        List<Post> list = postRepository.findByAuthorId(id);
        return list.stream().map(PostDTO::new).toList();
    }

    private User findUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    private Post createPost(PostInsertDTO dto, User user) {
        UserAuthor author = new UserAuthor(user.getId(), user.getName());
        return new Post(null, Instant.now(), dto.getTitle(), dto.getBody(), author);
    }

    private void savePostAndUser(Post post, User user) {
        post = postRepository.insert(post);
        user.getPosts().add(post);
        userRepository.save(user);
    }

    private Post updateDataPost(String id, PostUpdateDTO dto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        return post;
    }
}
