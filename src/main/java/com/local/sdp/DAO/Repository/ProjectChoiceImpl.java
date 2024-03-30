package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.ProjectChoiceDAO;
import com.local.sdp.Entity.ProjectChoice;
import com.local.sdp.Entity.Projects;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProjectChoiceImpl implements ProjectChoiceDAO {
    EntityManager entityManager;
    @Autowired
    ProjectChoiceImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(ProjectChoice projectChoice) {
        entityManager.merge(projectChoice);
    }

    @Override
    public List<ProjectChoice> getAllProjectChoiceByGroup(int groupId) {
        TypedQuery<ProjectChoice> projectsTypedQuery = entityManager.createQuery("Select p from ProjectChoice as p where p.group.id =: groupId", ProjectChoice.class);
        projectsTypedQuery.setParameter("groupId", groupId);
        return projectsTypedQuery.getResultList();
    }

    @Override
    public List<ProjectChoice> getAllProjectChoiceByStudent(int studentId) {
        TypedQuery<ProjectChoice> projectsTypedQuery = entityManager.createQuery("Select p from ProjectChoice as p where p.student.id =: studentId", ProjectChoice.class);
        projectsTypedQuery.setParameter("studentId", studentId);
        return projectsTypedQuery.getResultList();
    }

    @Override
    public ProjectChoice getProjectChoiceByProjectId(int projectId) {
        TypedQuery<ProjectChoice> projectsTypedQuery = entityManager.createQuery("Select p from ProjectChoice as p where p.projects.id =: projectId", ProjectChoice.class);
        projectsTypedQuery.setParameter("projectId", projectId);
        return projectsTypedQuery.getSingleResult();
    }

    @Override
    public List<ProjectChoice> getAll() {
        TypedQuery<ProjectChoice> projectsTypedQuery = entityManager.createQuery("From ProjectChoice", ProjectChoice.class);
        return projectsTypedQuery.getResultList();
    }

    @Override
    public ProjectChoice getProjectChoice(int projectId, int groupId) {
        TypedQuery<ProjectChoice> projectChoiceTypedQuery = entityManager.createQuery("Select p from ProjectChoice as p where p.projects.id =: projectId AND p.group.id =: groupId", ProjectChoice.class);
        projectChoiceTypedQuery.setParameter("projectId", projectId);
        projectChoiceTypedQuery.setParameter("groupId", groupId);
        return  projectChoiceTypedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void delete(int id) {
        ProjectChoice projectChoice = entityManager.find(ProjectChoice.class, id);
        entityManager.remove(projectChoice);
    }
}
