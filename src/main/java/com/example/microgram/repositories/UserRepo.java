package com.example.microgram.repositories;

import com.example.microgram.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAll();
    User findUserById(String id);
    void delete(User user);
    User save(User user);
    User findUserByName(String name);
    User findUserByEmail(String email);
    User findUserByLogin(String login);
    boolean existsByEmail(String email);
    boolean existsUserByLoginAndAndPassword(String login, String password);
}
