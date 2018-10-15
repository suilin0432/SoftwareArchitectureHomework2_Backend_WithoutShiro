package com.example.architecture.homework2_withoutshiro.repositories;

import com.example.architecture.homework2_withoutshiro.models.implementModels.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findOneByName(String name);
    Stream<Permission> findByName(Collection<String> nameList);
    Optional<Permission> findOneById(String id);
    Stream<Permission> findById(Collection<String> idList);
}
