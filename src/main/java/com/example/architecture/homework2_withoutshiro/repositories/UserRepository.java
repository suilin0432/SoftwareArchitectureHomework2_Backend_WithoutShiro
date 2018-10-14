package com.example.architecture.homework2_withoutshiro.repositories;

import com.example.architecture.homework2_withoutshiro.models.implementModels.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findOneByUsername(String username);
    Optional<User> findOneById(String Id);
}
