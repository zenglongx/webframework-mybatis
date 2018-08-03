package com.xx.webframework.service.impl;

import com.xx.webframework.domain.User;
import com.xx.webframework.mapper.UserMapper;
import com.xx.webframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll(Pageable pageable) {
        return userMapper.getAll();
    }
}
