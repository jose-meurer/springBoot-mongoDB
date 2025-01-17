package com.josemeurer.springBoot_mongoDB.repositories;

import com.josemeurer.springBoot_mongoDB.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByAuthorId(String id);

    @Query("{$or: [{'title': {$regex: ?0, $options: 'i' }}, {'body': {$regex: ?0, $options: 'i' }}, {'comments.body': { $regex: ?0, $options: 'i' }}]}")
    List<Post> searchText(String text);

}
