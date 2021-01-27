package com.example.microgram.repositories;

import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PublicationRepo extends MongoRepository<Publication, String> {
    Publication findPublicationById(String id);
    List<Publication> findPublicationsByUser(User user);
}
