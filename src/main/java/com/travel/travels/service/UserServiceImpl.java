package com.travel.travels.service;

import com.travel.travels.dao.UserDao;
import com.travel.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        if (userDao.findByUsername(user.getUsername()) == null) {
            userDao.save(user);
        } else {
            throw new RuntimeException("用户名已存在！");
        }
    }

    @Override
    public User login(User user) {
        User userDB = userDao.findByUsername(user.getUsername());
        if (userDB != null) {
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            }
            throw new RuntimeException("密码输入错误！");
        } else {
            throw new RuntimeException("用户名输入错误！");
        }
    }

}
