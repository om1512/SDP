package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.ResultDAO;
import com.local.sdp.Entity.Result;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ResultImpl implements ResultDAO {
    EntityManager entityManager;
    @Autowired
    ResultImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Result result) {
        entityManager.merge(result);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return entityManager.createQuery("delete from Result where id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public List<Result> getResult() {
        return entityManager.createQuery("from Result", Result.class).getResultList();
    }

    @Override
    public Result getResultById(int id) {
        return entityManager.find(Result.class, id);
    }
}
