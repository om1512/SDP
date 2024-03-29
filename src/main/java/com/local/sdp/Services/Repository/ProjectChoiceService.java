package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.ProjectChoiceDAO;
import com.local.sdp.Entity.ProjectChoice;
import com.local.sdp.Entity.Projects;
import com.local.sdp.Services.Interface.ProjectChoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectChoiceService implements ProjectChoiceServiceInterface {

    ProjectChoiceDAO projectChoiceDAO;
    @Autowired
    ProjectChoiceService (ProjectChoiceDAO projectChoiceDAO){
        this.projectChoiceDAO = projectChoiceDAO;
    }


    @Override
    public void save(ProjectChoice projectChoice) {
        projectChoiceDAO.save(projectChoice);
    }

    @Override
    public List<ProjectChoice> getAllProjectChoiceByGroup(int groupId) {
        return projectChoiceDAO.getAllProjectChoiceByGroup(groupId);
    }

    @Override
    public List<ProjectChoice> getAllProjectChoiceByStudent(int studentId) {
        return projectChoiceDAO.getAllProjectChoiceByStudent(studentId);
    }

    @Override
    public ProjectChoice getProjectChoiceByProjectId(int projectId) {
        return projectChoiceDAO.getProjectChoiceByProjectId(projectId);
    }

    @Override
    public List<ProjectChoice> getAll() {
        return projectChoiceDAO.getAll();
    }

    @Override
    public ProjectChoice getProjectChoice(int projectId, int groupId) {
        return projectChoiceDAO.getProjectChoice(projectId, groupId);
    }

    @Override
    public void delete(ProjectChoice projectChoice) {
        projectChoiceDAO.delete(projectChoice.getId());
    }
}
