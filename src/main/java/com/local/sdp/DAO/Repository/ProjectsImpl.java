package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.ProjectsDAO;
import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Projects;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class ProjectsImpl implements ProjectsDAO {
    EntityManager entityManager;
    @Autowired
    ProjectsImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Projects projects) {
        entityManager.merge(projects);
    }

    @Override
    public List<Projects> getAllProjects() {
        return entityManager.createQuery("from Projects", Projects.class).getResultList();
    }

    @Override
    public Projects getProjectById(int id) {
        return entityManager.find(Projects.class, id);
    }

    @Override
    public List<Projects> getAllProjectsVisibleToGroup(int groupId) {
        TypedQuery<Projects> projectsTypedQuery = entityManager.createQuery("select p from Projects as p where p.projectGroup.id=:groupId or p.isCustom = false", Projects.class);
        projectsTypedQuery.setParameter("groupId", groupId);
        return projectsTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void removeCustomProject(int groupId, int projectId) {
        Projects projectToDelete = entityManager.createQuery(
                        "SELECT p FROM Projects p WHERE p.projectGroup.id = :groupId AND p.id=:projectId AND p.isCustom = true",
                        Projects.class)
                .setParameter("groupId", groupId)
                .setParameter("projectId",projectId)
                .getSingleResult();
        if (projectToDelete != null) {
            entityManager.remove(projectToDelete);
        }
    }


}
