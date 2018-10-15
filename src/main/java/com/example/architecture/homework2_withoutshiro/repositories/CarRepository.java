package com.example.architecture.homework2_withoutshiro.repositories;

import com.example.architecture.homework2_withoutshiro.models.implementModels.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> {
    public Optional<Car> findOneByName(String name);
    public Optional<Car> findOneById(String id);
}
