package com.local.sdp.REST;


import com.local.sdp.Entity.Group;
import com.local.sdp.Entity.JoinRequest;
import com.local.sdp.Entity.Student;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import com.local.sdp.Services.Interface.JoinRequestServiceInterface;
import com.local.sdp.Services.Interface.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JoinRequestRESTController {
    @Autowired
    JoinRequestServiceInterface joinRequestServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @Autowired
    GroupServiceInterface groupServiceInterface;

    @PostMapping("/request/{studentId}/{groupId}")
    String sendRequest(@PathVariable int studentId, @PathVariable int groupId){
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setStatus(JoinRequest.JoinRequestStatus.PENDING);
        Student student = studentServiceInterface.findById(studentId);
        Group group = groupServiceInterface.getGroupById(groupId);
        if(student == null) return "Student does not exist";
        else joinRequest.setStudent(student);
        if(group == null) return "Group does not exist";
        else if(group.getStudentList().size() == 3) return "Group is full";
        else joinRequest.setGroup(group);
        joinRequestServiceInterface.save(joinRequest);
        return "Request Send By " + student.getName() + " to group " + group.getGroupName();
    }


    @GetMapping("/request")
    List<JoinRequest> getAllRequests(){
        return joinRequestServiceInterface.getRequest();
    }


    @GetMapping("/request/student/{studentId}")
    List<JoinRequest> getAllStudentRequests(@PathVariable int studentId){
        return joinRequestServiceInterface.allRequestOfStudent(studentId);
    }


    @GetMapping("/request/group/{groupId}")
    List<JoinRequest> getAllRequestByGroup(@PathVariable int groupId){
        return joinRequestServiceInterface.allRequestOfGroup(groupId);
    }

    @PostMapping("/request/{creatorId}/approve/{groupId}/{stuId}")
    String approveRequest(@PathVariable int creatorId, @PathVariable int groupId, @PathVariable int stuId){
        Student student =studentServiceInterface.findById(stuId);
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return "group does not exist";
        if(group.getStudent().getId() != creatorId) return "you are not authorized person";
        if(group.getStudentList().size() == 3) return "Group is full";
        JoinRequest joinRequest = joinRequestServiceInterface.getRequestByGroupIdStudentId(groupId, stuId);
        joinRequest.setStatus(JoinRequest.JoinRequestStatus.ACCEPTED);
        joinRequestServiceInterface.save(joinRequest);
        student.setGroup(group);
        studentServiceInterface.save(student);
        return "request approved";
    }

    @PostMapping("/request/{creatorId}/reject/{groupId}/{stuId}")
    String rejectRequest(@PathVariable int creatorId, @PathVariable int groupId, @PathVariable int stuId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return "group does not exist";
        if(group.getStudent().getId() != creatorId) return "you are not authorized person";
        JoinRequest joinRequest = joinRequestServiceInterface.getRequestByGroupIdStudentId(groupId, stuId);
        joinRequest.setStatus(JoinRequest.JoinRequestStatus.REJECTED);
        joinRequestServiceInterface.save(joinRequest);
        return "request rejected";
    }


}
