package com.example.microgram.repositories;

import com.example.microgram.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CommentRepo extends MongoRepository<Comment, String> {
}
