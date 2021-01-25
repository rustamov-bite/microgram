package com.example.microgram.repositories;

import com.example.microgram.entities.Follow;
import com.example.microgram.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepo extends MongoRepository<Follow, String> {
    List<Follow> findAll();
    Follow findFollowById(String id);
    List<Follow> findFollowByFollows(User follow);
}
