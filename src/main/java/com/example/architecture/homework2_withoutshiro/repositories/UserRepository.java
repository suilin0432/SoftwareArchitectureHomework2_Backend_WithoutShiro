package com.software.architecture.homework2.repositories;

import com.software.architecture.homework2.models.implementModels.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findOneByUsername(String username);
    Optional<User> findOneById(String Id);
}
