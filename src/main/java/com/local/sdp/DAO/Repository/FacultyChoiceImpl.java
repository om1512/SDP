package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.FacultyChoiceDAO;
import com.local.sdp.Entity.FacultyChoice;
import com.local.sdp.Entity.ProjectChoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;


@Repository
public class FacultyChoiceImpl implements FacultyChoiceDAO {

    EntityManager entityManager;
    FacultyChoiceImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(FacultyChoice facultyChoice) {
        entityManager.merge(facultyChoice);
    }

    @Override
    public List<FacultyChoice> getAllFacultyChoiceByGroup(int groupId) {
        TypedQuery<FacultyChoice> projectsTypedQuery = entityManager.createQuery("Select f from FacultyChoice as f where f.group.id =: groupId", FacultyChoice.class);
        projectsTypedQuery.setParameter("groupId", groupId);
        return projectsTypedQuery.getResultList();
    }

    @Override
    public FacultyChoice getFacultyChoiceById(int id) {
        return entityManager.find(FacultyChoice.class, id);
    }

    @Override
    public FacultyChoice getFacultyChoiceById(int groupId, int facultyId) {
        TypedQuery<FacultyChoice> projectsTypedQuery = entityManager.createQuery("Select f from FacultyChoice as f where f.group.id =: groupId and f.faculty.id =: facultyId", FacultyChoice.class);
        projectsTypedQuery.setParameter("groupId", groupId);
        projectsTypedQuery.setParameter("facultyId", facultyId);
        return projectsTypedQuery.getSingleResult();
    }

    @Override
    public List<FacultyChoice> getAll() {
        TypedQuery<FacultyChoice> projectsTypedQuery = entityManager.createQuery("From FacultyChoice", FacultyChoice.class);
        return projectsTypedQuery.getResultList();
    }
}
