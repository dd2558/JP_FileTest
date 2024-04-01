package com.study.erum.mapper;

import com.study.erum.model.User;

public interface UserMapper {
    void insertUser(User user);
    User getUserByUsername(String username);
}

