package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.UserDAO;
import com.local.sdp.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserImpl implements UserDAO {
    private EntityManager entityManager;

    public UserImpl() {
    }
    @Autowired
    public UserImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findByType(String type) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE type = :type", User.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
}

}
