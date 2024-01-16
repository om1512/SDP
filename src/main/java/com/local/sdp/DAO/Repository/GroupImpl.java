package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.GroupDAO;
import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Group;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GroupImpl implements GroupDAO {
    EntityManager entityManager;
    @Autowired
    GroupImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(Group group) {
        entityManager.merge(group);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return entityManager.createQuery("delete from Group where id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public List<Group> groupList() {
        return entityManager.createQuery("from Group", Group.class).getResultList();
    }

    @Override
    public Group getGroupById(int id) {
        return entityManager.find(Group.class, id);
    }
}
