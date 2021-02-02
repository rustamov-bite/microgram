package com.example.microgram.repositories;

import com.example.microgram.entities.Comment;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CommentRepo extends MongoRepository<Comment, String> {
    List<Comment> findAll();
    Comment findCommentById(String id);
    List<Comment> findCommentsByPublication(Publication publication);
    List<Comment> findCommentByUser(User user);
    void deleteCommentByUserAndId(User user, String id);
}
