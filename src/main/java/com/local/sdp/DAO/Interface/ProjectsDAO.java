package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsDAO{
    void save(Projects projects);
    List<Projects> getAllProjects();
    Projects getProjectById(int id);
    List<Projects> getAllProjectsVisibleToGroup(int groupId);
    void removeCustomProject(int groupId, int projectId);
}
