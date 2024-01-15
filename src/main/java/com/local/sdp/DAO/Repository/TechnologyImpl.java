package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.TechnologyDAO;
import com.local.sdp.Entity.Technologies;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class TechnologyImpl implements TechnologyDAO {
    EntityManager entityManager;
    @Autowired
    TechnologyImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Technologies technologies) {
        entityManager.merge(technologies);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return entityManager.createQuery("delete from Technologies where id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public List<Technologies> getTechnology() {
        return entityManager.createQuery("from Technologies",Technologies.class).getResultList();
    }

    @Override
    public Technologies getTechnologyById(int id) {
        return entityManager.find(Technologies.class,id);
    }

}
