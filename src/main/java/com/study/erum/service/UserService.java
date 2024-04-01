package com.study.erum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.erum.mapper.UserMapper;
import com.study.erum.model.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    
    
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
   }
