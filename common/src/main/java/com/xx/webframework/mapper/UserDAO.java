package com.xx.webframework.mapper;

import com.xx.webframework.domain.User;
import com.xx.webframework.domain.UserExample;

/**
 * UserDAO继承基类
 */
public interface UserDAO extends MyBatisBaseDao<User, Integer, UserExample> {
}