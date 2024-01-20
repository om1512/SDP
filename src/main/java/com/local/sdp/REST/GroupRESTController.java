package com.local.sdp.REST;


import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.Group;
import com.local.sdp.Entity.Projects;
import com.local.sdp.Entity.Student;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import com.local.sdp.Services.Interface.ProjectsServiceInterface;
import com.local.sdp.Services.Interface.StudentServiceInterface;
import com.local.sdp.Utils.group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolverExtensionsKt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupRESTController {
    @Autowired
    GroupServiceInterface groupServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @Autowired
    ProjectsServiceInterface projectsServiceInterface;

    @PostMapping("/group/createGroup/{stuId}")
    String createGroup(@RequestBody Group group, @PathVariable int stuId){
        Student student = studentServiceInterface.findById(stuId);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        group.setStudentList(studentList);
        group.setStudent(student);
        groupServiceInterface.save(group);
        student.setGroup(group);
        studentServiceInterface.save(student);
        return "group created by : " + student.getName();
    }

    @PostMapping("/group/joinGroup/{stuId}/{groupId}")
    String joinGroup(@PathVariable int stuId, @PathVariable int groupId){
        Student student =studentServiceInterface.findById(stuId);
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null){
            return "Group does not exist";
        }else if(group.getStudentList().size() == 3){
            return "Group is full";
        }
        student.setGroup(group);
        studentServiceInterface.save(student);
        return "Student "+student.getName() + " joined the group " + group.getGroupName();
    }


    @GetMapping("/group")
    List<Group> getAllGroups(){
        return groupServiceInterface.groupList();
    }

    @GetMapping("/group/{id}")
    Group getGroupById(@PathVariable int id){
        return groupServiceInterface.getGroupById(id);
    }

    @PostMapping("/group/leaveGroup/{stuId}/{groupId}")
    String leaveGroup(@PathVariable int stuId, @PathVariable int groupId){
        Group group = groupServiceInterface.getGroupById(groupId);
        Student student = studentServiceInterface.findById(stuId);
        student.setGroup(null);
        studentServiceInterface.save(student);
        return "Student " + student.getName() + " leaved group " + group.getGroupName();
    }

    @PostMapping("/group/deleteGroup/{stuId}/{groupId}")
    String deleteGroup(@PathVariable int stuId, @PathVariable int groupId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(stuId != group.getStudent().getId()) return "Unauthorized Student : trying to delete group";
        List<Student> studentList = group.getStudentList();
        for(Student student  : studentList){
            student.setGroup(null);
            studentServiceInterface.save(student);
        }
        groupServiceInterface.delete(groupId);
        return "trying to delete Group";
    }

    @PostMapping("/group/assignFaculty/{facId}/{groupId}")
    String assignFaculty(@PathVariable int facId, @PathVariable int groupId){
        Faculty faculty = facultyServiceInterface.getFacultyById(facId);
        Group group = groupServiceInterface.getGroupById(groupId);
        group.setFaculty(faculty);
        groupServiceInterface.save(group);
        return "Faculty " + faculty.getName() + " Assigned to Group " + group.getGroupName();
    }

    @PostMapping("/group/allocateProject/{groupId}/{proId}")
    String allocateProject(@PathVariable int groupId, @PathVariable int proId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return "group does not exist";
        Projects projects = projectsServiceInterface.getProjectById(proId);
        group.setProject(projects);
        return "project " + projects.getName() + " allocated to " + group.getGroupName();
    }


    @GetMapping("/group/assignRank")
    void assignRank(){
        List<Group> groupList = groupServiceInterface.groupList();
        List<group> groups = new ArrayList<>();
        for(Group g : groupList){
            groups.add(new group(g.getId(), g.getStudentList()));
        }
        Collections.sort(groups);
        for(int i=1;i<=groups.size();i++){
            Group groupToAssignRank = groupServiceInterface.getGroupById(groups.get(i-1).groupId);
            groupToAssignRank.setRank(i);
            groupServiceInterface.save(groupToAssignRank);
        }
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Group Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
