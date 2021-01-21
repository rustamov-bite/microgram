package com.example.microgram.repositories;

import com.example.microgram.entities.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepo extends MongoRepository<Follow, String> {
}
