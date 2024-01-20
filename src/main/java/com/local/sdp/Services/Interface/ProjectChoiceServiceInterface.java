package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.ProjectChoice;
import com.local.sdp.Entity.Projects;

import java.util.List;

public interface ProjectChoiceServiceInterface {
    void save(ProjectChoice projectChoice);
    List<ProjectChoice> getAllProjectChoiceByGroup(int groupId);

    List<ProjectChoice> getAllProjectChoiceByStudent(int studentId);

    ProjectChoice getProjectChoiceByProjectId(int projectId);
    List<ProjectChoice> getAll();
}
