package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.StudentDAO;
import com.local.sdp.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class StudentImpl implements StudentDAO {
    private EntityManager entityManager;

    public StudentImpl() {
    }

    @Autowired
    public StudentImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.merge(student);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findByUserId(int id) {
        TypedQuery<Student> typedQuery = entityManager.createQuery("Select s FROM Student s where s.user.id =: userId",Student.class);
        typedQuery.setParameter("userId", id);
        return typedQuery.getSingleResult();
    }


    @Override
    @Transactional
    public void deleteById(int id) {
        Student student = entityManager.find(Student.class,id);
        entityManager.remove(student);
    }
}
