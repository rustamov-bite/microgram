package com.example.microgram.repositories;

import com.example.microgram.entities.PubImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PubImageRepo extends MongoRepository<PubImage, String> {
    PubImage findPubImageById(String id);
    void deletePubImageById(String id);
}
