package com.josemeurer.springBoot_mongoDB.repositories;

import com.josemeurer.springBoot_mongoDB.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
