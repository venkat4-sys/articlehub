package com.ait.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ait.repository.UserRepository;

@Service 
public class UserService {

    @Autowired 
    private  UserRepository userRepository;

    public boolean signup(Map<String,Object> user){
        return userRepository.signUp(user);
    }

    public boolean login(Map<String,Object> user){
        return userRepository.login(user);
    }

}
