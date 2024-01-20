package com.local.sdp.REST;


import com.local.sdp.Entity.Group;
import com.local.sdp.Entity.Projects;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import com.local.sdp.Services.Interface.ProjectsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectsRESTController {

    @Autowired
    ProjectsServiceInterface projectsServiceInterface;

    @Autowired
    GroupServiceInterface groupServiceInterface;

    @PostMapping("/project")
    String addProject(@RequestBody Projects projects){
        projects.setCustom(false);
        projectsServiceInterface.save(projects);
        return "Project saved";
    }

    @PostMapping("/project/custom/{groupId}")
    String addCustomProject(@RequestBody Projects projects, @PathVariable int groupId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return "Group does not exist";
        projects.setProjectGroup(group);
        projects.setCustom(true);
        projectsServiceInterface.save(projects);
        return "Custom project saved";
    }
    @GetMapping("/project")
    List<Projects> getAllProjects(){
        return projectsServiceInterface.getAllProjects();
    }

    @GetMapping("/project/{proId}")
    Projects getProjectById(@PathVariable int proId){
        return projectsServiceInterface.getProjectById(proId);
    }

    @GetMapping("/project/group/{groupId}")
    List<Projects> getAllProjectsVisibleToGroup(@PathVariable int groupId){
        return projectsServiceInterface.getAllProjectsVisibleToGroup(groupId);
    }

    @DeleteMapping("/project/{groupId}/{projectId}")
    String removeCustomProject(@PathVariable int groupId,@PathVariable int projectId){
        projectsServiceInterface.removeCustomProject(groupId, projectId);
        return "Project deleted successfully";
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Project Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
