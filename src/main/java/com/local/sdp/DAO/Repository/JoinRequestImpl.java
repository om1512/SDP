package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.JoinRequestDAO;

import com.local.sdp.Entity.JoinRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class JoinRequestImpl implements JoinRequestDAO {

    EntityManager entityManager;
    @Autowired
    JoinRequestImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(JoinRequest joinRequest) {
        entityManager.merge(joinRequest);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return entityManager.createQuery("delete from JoinRequest where id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public List<JoinRequest> getRequest() {
        return entityManager.createQuery("from JoinRequest", JoinRequest.class).getResultList();
    }

    @Override
    public JoinRequest getRequestById(int id) {
        return entityManager.find(JoinRequest.class, id);
    }

    @Override
    public List<JoinRequest> allRequestOfStudent(int studentId) {
        TypedQuery<JoinRequest> query = entityManager.createQuery("SELECT jr FROM JoinRequest jr WHERE jr.student.id = :studentId", JoinRequest.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public List<JoinRequest> allRequestOfGroup(int groupId) {
        TypedQuery<JoinRequest> query = entityManager.createQuery("SELECT jr FROM JoinRequest jr WHERE jr.group.id = :groupId", JoinRequest.class);
        query.setParameter("groupId", groupId);
        return query.getResultList();
    }

    @Override
    public JoinRequest getRequestByGroupIdStudentId(int groupId, int studentId) {
        TypedQuery<JoinRequest> query = entityManager.createQuery("SELECT jr FROM JoinRequest jr WHERE jr.group.id = :groupId and jr.student.id = :studentId", JoinRequest.class);
        query.setParameter("groupId", groupId);
        query.setParameter("studentId", studentId);
        return query.getSingleResult();
    }
}
