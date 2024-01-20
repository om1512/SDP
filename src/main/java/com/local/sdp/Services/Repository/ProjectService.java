package com.local.sdp.Services.Repository;


import com.local.sdp.DAO.Interface.ProjectsDAO;
import com.local.sdp.Entity.Projects;
import com.local.sdp.Services.Interface.ProjectsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService implements ProjectsServiceInterface {

    ProjectsDAO projectsDAO;
    @Autowired
    ProjectService(ProjectsDAO projectsDAO){
        this.projectsDAO = projectsDAO;
    }

    @Override
    public void save(Projects projects) {
        projectsDAO.save(projects);
    }

    @Override
    public List<Projects> getAllProjects() {
        return projectsDAO.getAllProjects();
    }

    @Override
    public Projects getProjectById(int id) {
        return  projectsDAO.getProjectById(id);
    }

    @Override
    public List<Projects> getAllProjectsVisibleToGroup(int groupId) {
        return projectsDAO.getAllProjectsVisibleToGroup(groupId);
    }

    @Override
    public void removeCustomProject(int groupId, int projectId) {
        projectsDAO.removeCustomProject(groupId, projectId);
    }
}
