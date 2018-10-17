package com.example.architecture.homework2_withoutshiro.services;

import com.example.architecture.homework2_withoutshiro.repositories.CarRepository;
import com.example.architecture.homework2_withoutshiro.repositories.CommodityRepository;
import com.example.architecture.homework2_withoutshiro.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService {
    private Config config;
    private CarRepository carRepository;
    private CommodityRepository commodityRepository;

    @Autowired
    public FunctionService(Config config,
                           CarRepository carRepository,
                           CommodityRepository commodityRepository){
        this.carRepository = carRepository;
        this.config = config;
        this.commodityRepository = commodityRepository;
    }
}
