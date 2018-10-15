package com.example.architecture.homework2_withoutshiro.repositories;

import com.example.architecture.homework2_withoutshiro.models.implementModels.Permission;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findOneByName(String name);
    Stream<Role> findByName(Collection<String> nameList);
    Optional<Role> findOneById(String id);
    Stream<Role> findById(Collection<String> idList);
}
