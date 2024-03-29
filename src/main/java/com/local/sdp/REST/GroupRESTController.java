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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/group")
@CrossOrigin(origins = "http://localhost:4200")

public class GroupRESTController {
    @Autowired
    GroupServiceInterface groupServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @Autowired
    ProjectsServiceInterface projectsServiceInterface;

    @PostMapping("/createGroup/{stuId}")
    ResponseEntity<String> createGroup(@RequestBody Group group, @PathVariable int stuId){
        Student student = studentServiceInterface.findById(stuId);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        group.setStudentList(studentList);
        group.setStudent(student);
        groupServiceInterface.save(group);
        student.setGroup(group);
        studentServiceInterface.save(student);
        return new ResponseEntity<>("group created by : " + student.getName(), HttpStatus.OK);
    }

    @PostMapping("/joinGroup/{stuId}/{groupId}")
    ResponseEntity<String> joinGroup(@PathVariable int stuId, @PathVariable int groupId){
        Student student =studentServiceInterface.findById(stuId);
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null){
            return new ResponseEntity<>("Group does not exist", HttpStatus.NOT_FOUND);
        }else if(group.getStudentList().size() == 3){
            return new ResponseEntity<>("Group is full", HttpStatus.LOCKED);
        }
        student.setGroup(group);
        studentServiceInterface.save(student);
        return new ResponseEntity<>("Student "+student.getName() + " joined the group " + group.getGroupName(), HttpStatus.OK);
    }


    @GetMapping("")
    ResponseEntity<List<Group>> getAllGroups(){
        return new ResponseEntity<>(groupServiceInterface.groupList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Group> getGroupById(@PathVariable int id){
        return new ResponseEntity<>(groupServiceInterface.getGroupById(id), HttpStatus.OK);
    }

    @PostMapping("/leaveGroup/{stuId}/{groupId}")
    ResponseEntity<String> leaveGroup(@PathVariable int stuId, @PathVariable int groupId){
        Group group = groupServiceInterface.getGroupById(groupId);
        Student student = studentServiceInterface.findById(stuId);
        student.setGroup(null);
        studentServiceInterface.save(student);
        return new ResponseEntity<>("Student " + student.getName() + " leaved group " + group.getGroupName(), HttpStatus.OK );
    }

    @PostMapping("/deleteGroup/{stuId}/{groupId}")
    ResponseEntity<String> deleteGroup(@PathVariable int stuId, @PathVariable int groupId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(stuId != group.getStudent().getId()) return new ResponseEntity<>("Unauthorized Student : trying to delete group", HttpStatus.OK);
        List<Student> studentList = group.getStudentList();
        for(Student student  : studentList){
            student.setGroup(null);
            studentServiceInterface.save(student);
        }
        groupServiceInterface.delete(groupId);
        return new ResponseEntity<>("trying to delete Group", HttpStatus.OK);
    }

    @PostMapping("/assignFaculty/{facId}/{groupId}")
    ResponseEntity<String> assignFaculty(@PathVariable int facId, @PathVariable int groupId){
        Faculty faculty = facultyServiceInterface.getFacultyById(facId);
        Group group = groupServiceInterface.getGroupById(groupId);
        group.setFaculty(faculty);
        groupServiceInterface.save(group);
        return new ResponseEntity<>("Faculty " + faculty.getName() + " Assigned to Group " + group.getGroupName(), HttpStatus.OK);
    }

    @PostMapping("/allocateProject/{groupId}/{proId}")
    ResponseEntity<String> allocateProject(@PathVariable int groupId, @PathVariable int proId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return new ResponseEntity<>("group does not exist", HttpStatus.OK);
        Projects projects = projectsServiceInterface.getProjectById(proId);
        group.setProject(projects);
        groupServiceInterface.save(group);
        return new ResponseEntity<>("project " + projects.getName() + " allocated to " + group.getGroupName(), HttpStatus.OK);
    }


    @PostMapping("/assignRank")
    ResponseEntity<String> assignRank(){
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
        return new ResponseEntity<>("Rank assigned", HttpStatus.OK);
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
