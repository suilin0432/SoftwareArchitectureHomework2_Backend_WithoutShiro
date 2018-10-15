package com.example.architecture.homework2_withoutshiro.controllers;

import com.example.architecture.homework2_withoutshiro.common.Objects;
import com.example.architecture.homework2_withoutshiro.models.OperationType;
import com.example.architecture.homework2_withoutshiro.models.ResourceType;
import com.example.architecture.homework2_withoutshiro.models.exceptionModels.AuthException;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Car;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Commodity;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Permission;
import com.example.architecture.homework2_withoutshiro.models.implementModels.User;
import com.example.architecture.homework2_withoutshiro.models.requestModels.ObjectData;
import com.example.architecture.homework2_withoutshiro.repositories.CarRepository;
import com.example.architecture.homework2_withoutshiro.repositories.CommodityRepository;
import com.example.architecture.homework2_withoutshiro.repositories.PermissionRepository;
import com.example.architecture.homework2_withoutshiro.services.AuthService;
import com.example.architecture.homework2_withoutshiro.services.env.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Api(description = "功能性操作")
@RestController
@RequestMapping(value = "/function/")
public class FunctionController {
    private Config config;
    private AuthService authService;
    private CarRepository carRepository;
    private CommodityRepository commodityRepository;
    private PermissionRepository permissionRepository;

    @Autowired
    public FunctionController(Config config,
                              AuthService authService,
                              CarRepository carRepository,
                              CommodityRepository commodityRepository,
                              PermissionRepository permissionRepository){
        this.config = config;
        this.authService = authService;
        this.carRepository = carRepository;
        this.commodityRepository = commodityRepository;
        this.permissionRepository = permissionRepository;
    }

    @ApiOperation(value = "添加Commodity", notes = "")
    @RequestMapping(value = "/commodity/insert", method = RequestMethod.POST)
    public ResponseEntity addCommodity(@RequestBody ObjectData data, HttpSession session) throws AuthException {
        String name = data.name;
        String description = data.description;
        Long price = data.price;
        if(!Objects.isNotNull(name, description, price)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.INSERT, ResourceType.COMMODITY);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Commodity> commodityFound = commodityRepository.findOneByName(name);
        if(!commodityFound.isPresent()){
            throw new AuthException(10110, config.getExceptionsMap().get(10110));
        }
        Commodity commodity = new Commodity();
        commodity.setName(name);
        commodity.setName(description);
        commodity.setPrice(price);
        return new ResponseEntity(commodityRepository.save(commodity), HttpStatus.OK);
    }

    @ApiOperation(value = "删除Commodity", notes = "")
    @RequestMapping(value = "/commodity/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCommodity(@PathVariable String id, HttpSession session) throws AuthException {
        if(!Objects.isNotNull(id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.DELETE, ResourceType.COMMODITY);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Commodity> commodityFound = commodityRepository.findOneById(id);
        if(!commodityFound.isPresent()){
            throw new AuthException(10110, config.getExceptionsMap().get(10110));
        }
        commodityRepository.delete(commodityFound.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "修改Commodity", notes = "")
    @RequestMapping(value = "/commodity/update", method = RequestMethod.POST)
    public ResponseEntity updateCommodity(@RequestBody ObjectData data, HttpSession session) throws AuthException {
        String id = data.id;
        String name = data.name;
        String description = data.description;
        Long price = data.price;
        if(!Objects.isNotNull(id, name, description, price)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.UPDATE, ResourceType.COMMODITY);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Commodity> commodityModityFound = commodityRepository.findOneById(id);
        if(!commodityModityFound.isPresent()){
            throw new AuthException(10111, config.getExceptionsMap().get(10111));
        }
        Optional<Commodity> commodityFound = commodityRepository.findOneByName(name);
        if(!commodityFound.isPresent()){
            throw new AuthException(10110, config.getExceptionsMap().get(10110));
        }
        Commodity commodity = commodityModityFound.get();
        commodity.setName(name);
        commodity.setName(description);
        commodity.setPrice(price);
        return new ResponseEntity(commodityRepository.save(commodity), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有商品列表Commodity", notes = "")
    @RequestMapping(value = "/commodity/find", method = RequestMethod.GET)
    public ResponseEntity findCommodity(HttpSession session) throws AuthException {
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.SEARCH, ResourceType.COMMODITY);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        List<Commodity> commodityList = commodityRepository.findAll();
        return new ResponseEntity(commodityList, HttpStatus.OK);
    }

    @ApiOperation(value = "添加Car", notes = "")
    @RequestMapping(value = "/car/insert", method = RequestMethod.POST)
    public ResponseEntity addCar(@RequestBody ObjectData data, HttpSession session) throws AuthException {
        String name = data.name;
        String description = data.description;
        Long price = data.price;
        if(!Objects.isNotNull(name, description, price)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.INSERT, ResourceType.CAR);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Car> carFound = carRepository.findOneByName(name);
        if(!carFound.isPresent()){
            throw new AuthException(10110, config.getExceptionsMap().get(10110));
        }
        Car car = new Car();
        car.setName(name);
        car.setName(description);
        car.setPrice(price);
        return new ResponseEntity(carRepository.save(car), HttpStatus.OK);
    }

    @ApiOperation(value = "删除Car", notes = "")
    @RequestMapping(value = "/car/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCar(@PathVariable String id, HttpSession session) throws AuthException {
        if(!Objects.isNotNull(id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.DELETE, ResourceType.CAR);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Car> carFound = carRepository.findOneById(id);
        if(!carFound.isPresent()){
            throw new AuthException(10110, config.getExceptionsMap().get(10110));
        }
        carRepository.delete(carFound.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "修改Car", notes = "")
    @RequestMapping(value = "/car/update", method = RequestMethod.POST)
    public ResponseEntity updateCar(@RequestBody ObjectData data, HttpSession session) throws AuthException {
        String id = data.id;
        String name = data.name;
        String description = data.description;
        Long price = data.price;
        if(!Objects.isNotNull(id, name, description, price)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.UPDATE, ResourceType.CAR);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Car> caryModityFound = carRepository.findOneById(id);
        if(!caryModityFound.isPresent()){
            throw new AuthException(10111, config.getExceptionsMap().get(10111));
        }
        Optional<Car> carFound = carRepository.findOneByName(name);
        if(!carFound.isPresent()){
            throw new AuthException(10110, config.getExceptionsMap().get(10110));
        }
        Car car = carFound.get();
        car.setName(name);
        car.setName(description);
        car.setPrice(price);
        return new ResponseEntity(carRepository.save(car), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有汽车列car表", notes = "")
    @RequestMapping(value = "/car/find", method = RequestMethod.GET)
    public ResponseEntity findCar(HttpSession session) throws AuthException {
        User user = authService.getUserFromSession(session);
        Optional<Permission> permissionFound = permissionRepository.findOneByOperationTypeAndResourceType(OperationType.SEARCH, ResourceType.CAR);
        if(!permissionFound.isPresent()){
            throw new AuthException(1019, config.getExceptionsMap().get(1019));
        }
        Permission permission = permissionFound.get();
        if(!authService.userHasAuth(user, permission)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        List<Car> carList = carRepository.findAll();
        return new ResponseEntity(carList, HttpStatus.OK);
    }
}
