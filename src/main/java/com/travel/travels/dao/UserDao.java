package com.travel.travels.dao;

import com.travel.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    // 注册用户
    void save(User user);
    // 根据用户名查询用户
    User findByUsername(String username);
}
