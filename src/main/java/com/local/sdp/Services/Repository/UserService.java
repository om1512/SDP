package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.UserDAO;
import com.local.sdp.Entity.User;
import com.local.sdp.Services.Interface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserServiceInterface {
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserService() {
    }

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findByType(String type) {
        return userDAO.findByType(type);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }



}
