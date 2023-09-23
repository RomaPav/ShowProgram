package com.example.microservice.repository;

import com.example.microservice.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<Show,String> {
}
