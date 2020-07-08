package com.travel.travels.service;

        import com.travel.travels.entity.User;

public interface UserService {
    void register(User user);
    User login(User user);
}

