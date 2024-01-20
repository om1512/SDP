package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Projects;

import java.util.List;

public interface ProjectsServiceInterface {
    void save(Projects projects);
    List<Projects> getAllProjects();
    Projects getProjectById(int id);
    List<Projects> getAllProjectsVisibleToGroup(int groupId);
    void removeCustomProject(int groupId, int projectId);
}
