package com.xx.webframework.service;

import com.xx.webframework.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService{
    List<User> findAll(Pageable pageable);
}
