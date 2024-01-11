package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.User;

import java.util.List;

public interface UserServiceInterface {
    void save(User user);
    User findById(int id);
    List<User> findByType(String type);
    List<User> findAll();
    User findByEmail(String email);
    User update(User user);
    void delete(int id);

}
