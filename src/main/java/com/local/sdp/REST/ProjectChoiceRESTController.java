package com.local.sdp.REST;

import com.local.sdp.Entity.Group;
import com.local.sdp.Entity.ProjectChoice;
import com.local.sdp.Entity.Projects;
import com.local.sdp.Entity.Student;
import com.local.sdp.Services.Interface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/projectChoice")
@CrossOrigin(origins = "http://localhost:4200")

public class ProjectChoiceRESTController {
    @Autowired
    ProjectChoiceServiceInterface projectChoiceServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @Autowired
    GroupServiceInterface groupServiceInterface;

    @Autowired
    ProjectsServiceInterface projectsServiceInterface;

    @PostMapping("/{groupId}/{studentId}/{projectId}")
    String addProjectChoice(@PathVariable int groupId, @PathVariable int studentId, @PathVariable int projectId){
        Student student = studentServiceInterface.findById(studentId);
        if(student == null) return "student does not exist";
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return "group does not exist";
        Projects projects = projectsServiceInterface.getProjectById(projectId);
        if(projects == null) return "project does not exist";
        ProjectChoice projectChoice = new ProjectChoice();
        projectChoice.setGroup(group);
        projectChoice.setProjects(projects);
        projectChoice.setStudent(student);
        projectChoice.setPriority(projectChoiceServiceInterface.getAllProjectChoiceByGroup(groupId).size() + 1);
        projectChoiceServiceInterface.save(projectChoice);
        return "project choice added";
    }

    @PostMapping("/changePriority/{groupId}/{projectId}/{priority}")
    String changePriority(@PathVariable int groupId,@PathVariable int projectId, @PathVariable int priority){
        ProjectChoice projectChoice = projectChoiceServiceInterface.getProjectChoiceByProjectId(projectId);
        if(projectChoice == null) return "project choice does not exist";
        List<ProjectChoice> getAllProjectChoice = projectChoiceServiceInterface.getAllProjectChoiceByGroup(groupId);
        for(ProjectChoice choice : getAllProjectChoice){
            if(priority < projectChoice.getPriority() && choice.getPriority() >= priority && choice.getPriority() < projectChoice.getPriority()){
                choice.setPriority(choice.getPriority() + 1);
            }else if(priority > projectChoice.getPriority() && choice.getPriority() <= priority && choice.getPriority() > projectChoice.getPriority()){
                choice.setPriority(choice.getPriority() - 1);
            }
        }
        projectChoice.setPriority(priority);
        projectChoiceServiceInterface.save(projectChoice);
        return "priority changed";
    }

    @GetMapping("/{groupId}")
    List<ProjectChoice> getAllChoiceOfGroup(@PathVariable int groupId){
        return projectChoiceServiceInterface.getAllProjectChoiceByGroup(groupId);
    }


    @GetMapping("/assignProject")
    void assignProject(){
        List<Group> groups = groupServiceInterface.groupList();
        List<Group> sortedByRank = sortGroups(groups);
        Set<Integer> allocatedProject = new HashSet<>();

        for(Group group : sortedByRank){
            Map<Integer, Integer> projectMap = new HashMap<>();
            List<ProjectChoice> projectChoices = projectChoiceServiceInterface.getAllProjectChoiceByGroup(group.getId());
            for(ProjectChoice projectChoice : projectChoices){
                projectMap.put(projectChoice.getPriority(), projectChoice.getProjects().getId());
            }
            List<Integer> priorities = new ArrayList<>(projectMap.keySet());
            Collections.sort(priorities);
            int i = 0;
            while(i < priorities.size()){
                if(allocatedProject.add(projectMap.get(priorities.get(i)))){
                    group.setProject(projectsServiceInterface.getProjectById(projectMap.get(priorities.get(i))));
                    groupServiceInterface.save(group);
                    break;
                }
                i++;
            }
        }
    }

    private List<Group> sortGroups(List<Group> groups) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Group group : groups){
            map.put(group.getRank(), group.getId());
        }
        List<Integer> employeeByKey = new ArrayList<>(map.keySet());
        Collections.sort(employeeByKey);
        List<Group> ans = new ArrayList<>();
        for(int i:employeeByKey){
            ans.add(groupServiceInterface.getGroupById(map.get(i)));
        }



        return ans;
    }
}
