package com.example.architecture.homework2_withoutshiro.repositories;

import com.example.architecture.homework2_withoutshiro.models.implementModels.Commodity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommodityRepository extends MongoRepository<Commodity, String> {
    public Optional<Commodity> findOneById(String id);
    public Optional<Commodity> findOneByName(String name);
}
