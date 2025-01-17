package com.josemeurer.springBoot_mongoDB.repositories;

import com.josemeurer.springBoot_mongoDB.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
