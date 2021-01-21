package com.example.microgram.repositories;

import com.example.microgram.entities.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PublicationRepo extends MongoRepository<Publication, String> {
}
