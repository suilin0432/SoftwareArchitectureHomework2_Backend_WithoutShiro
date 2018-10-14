package com.software.architecture.homework2.controllers;

import com.software.architecture.homework2.models.requestModels.Data;
import com.software.architecture.homework2.repositories.UserRepository;
import com.software.architecture.homework2.services.BasicService;
import com.software.architecture.homework2.services.env.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(description = "基本操作")
@RestController
@RequestMapping(value = "/")
public class BasicController {
    Config config;
    BasicService basicService;
    UserRepository userRepository;

    @Autowired
    public BasicController(Config config, BasicService basicService, UserRepository userRepository){
        this.config = config;
        this.basicService = basicService;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "注册", notes = "")
    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ResponseEntity userRegister(@RequestBody Data data, HttpSession session){
        return null;
    }

    @ApiOperation(value = "登陆", notes = "")
    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ResponseEntity userLogin(@RequestBody Data data, HttpSession session){
        return null;
    }
}
