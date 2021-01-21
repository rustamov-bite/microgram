package com.example.microgram.repositories;

import com.example.microgram.entities.Like;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LikeRepo extends MongoRepository<Like, String> {
}
