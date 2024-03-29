package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.ProjectChoice;
import com.local.sdp.Entity.Projects;

import java.util.List;

public interface ProjectChoiceDAO {
    void save(ProjectChoice projectChoice);
    List<ProjectChoice> getAllProjectChoiceByGroup(int groupId);

    List<ProjectChoice> getAllProjectChoiceByStudent(int studentId);

    ProjectChoice getProjectChoiceByProjectId(int projectId);

    List<ProjectChoice> getAll();

    ProjectChoice getProjectChoice(int projectId, int groupId);
    void delete(int id);
}
