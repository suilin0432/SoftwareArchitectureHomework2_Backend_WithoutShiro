package com.example.architecture.homework2_withoutshiro.services;

import com.example.architecture.homework2_withoutshiro.repositories.UserRepository;
import com.example.architecture.homework2_withoutshiro.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
    UserRepository userRepository;
    Config config;
    @Autowired
    public BasicService(UserRepository userRepository,
                        Config config){
        this.userRepository = userRepository;
        this.config = config;
    }
    public boolean isRightUsername(String username){
        return (username.length()>=6 && username.length()<=16 && !userRepository.findOneById(username).isPresent());
    }
    public boolean isRightPassword(String password){
        return password.length()>=6 && password.length()<=16;
    }
}
