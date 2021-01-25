package com.example.microgram.repositories;

import com.example.microgram.entities.Like;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LikeRepo extends MongoRepository<Like, String> {
    Like findLikeById(String id);
    boolean existsLikeByUserAndPublication(User user, Publication publication);
}
