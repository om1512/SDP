package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.FacultyDAO;
import com.local.sdp.Entity.Faculty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class FacultyImpl implements FacultyDAO {
    private EntityManager entityManager;

    public FacultyImpl() {
    }

    @Autowired
    public FacultyImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Faculty faculty) {
        entityManager.merge(faculty);
    }

    @Override
    public List<Faculty> getFaculties() {
        TypedQuery<Faculty> typedQuery = entityManager.createQuery("FROM Faculty", Faculty.class);
        return typedQuery.getResultList();
    }

    @Override
    public Faculty getFacultyById(int id) {
        return entityManager.find(Faculty.class, id);
    }

    @Override
    public Faculty getFacultyByUserId(int id) {
        TypedQuery<Faculty> facultyTypedQuery = entityManager.createQuery("SELECT f FROM Faculty f WHERE f.user.id =:userId", Faculty.class);
        facultyTypedQuery.setParameter("userId",id);
        return facultyTypedQuery.getSingleResult();
    }

    @Override
    public void deleteById(int id) {
        Faculty faculty = entityManager.find(Faculty.class, id);
        entityManager.remove(faculty);
    }
}
