package com.local.sdp.REST;


import com.local.sdp.Entity.Group;
import com.local.sdp.Entity.JoinRequest;
import com.local.sdp.Entity.Student;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import com.local.sdp.Services.Interface.JoinRequestServiceInterface;
import com.local.sdp.Services.Interface.StudentServiceInterface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class JoinRequestRESTController {
    @Autowired
    JoinRequestServiceInterface joinRequestServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @Autowired
    GroupServiceInterface groupServiceInterface;

    @PostMapping("/{studentId}/{groupId}")
    ResponseEntity<String> sendRequest(@PathVariable int studentId, @PathVariable int groupId){
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setStatus(JoinRequest.JoinRequestStatus.PENDING);
        Student student = studentServiceInterface.findById(studentId);
        Group group = groupServiceInterface.getGroupById(groupId);
        if(student == null) return new ResponseEntity<>("Student does not exist", HttpStatus.NOT_FOUND);
        else joinRequest.setStudent(student);
        if(group == null) return new ResponseEntity<>("Group does not exist", HttpStatus.NOT_FOUND);
        else if(group.getStudentList().size() == 3) return new ResponseEntity<>("Group is full", HttpStatus.LOCKED);
        else joinRequest.setGroup(group);
        joinRequestServiceInterface.save(joinRequest);
        return new ResponseEntity<>("Request Send By " + student.getName() + " to group " + group.getGroupName(), HttpStatus.OK);
    }


    @GetMapping("")
    ResponseEntity<List<JoinRequest>> getAllRequests(){
        return new ResponseEntity<>(joinRequestServiceInterface.getRequest(), HttpStatus.OK);
    }


    @GetMapping("/student/{studentId}")
    ResponseEntity<List<JoinRequest>> getAllStudentRequests(@PathVariable int studentId){
        return new ResponseEntity<>(joinRequestServiceInterface.allRequestOfStudent(studentId), HttpStatus.OK);
    }


    @GetMapping("/group/{groupId}")
    ResponseEntity<List<JoinRequest>> getAllRequestByGroup(@PathVariable int groupId){
        return new ResponseEntity<>(joinRequestServiceInterface.allRequestOfGroup(groupId), HttpStatus.OK);
    }

    @PostMapping("/{creatorId}/approve/{groupId}/{stuId}")
    ResponseEntity<String> approveRequest(@PathVariable int creatorId, @PathVariable int groupId, @PathVariable int stuId){
        Student student =studentServiceInterface.findById(stuId);
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return new ResponseEntity<>("group does not exist", HttpStatus.NOT_FOUND);
        if(group.getStudent().getId() != creatorId) return new ResponseEntity<>("you are not authorized person", HttpStatus.BAD_REQUEST);
        if(group.getStudentList().size() == 3) return new ResponseEntity<>("Group is full", HttpStatus.LOCKED);
        JoinRequest joinRequest = joinRequestServiceInterface.getRequestByGroupIdStudentId(groupId, stuId);
        joinRequest.setStatus(JoinRequest.JoinRequestStatus.ACCEPTED);
        joinRequestServiceInterface.save(joinRequest);
        student.setGroup(group);
        studentServiceInterface.save(student);
        return new ResponseEntity<>("request approved", HttpStatus.OK);
    }

    @PostMapping("/{creatorId}/reject/{groupId}/{stuId}")
    ResponseEntity<String> rejectRequest(@PathVariable int creatorId, @PathVariable int groupId, @PathVariable int stuId){
        Group group = groupServiceInterface.getGroupById(groupId);
        if(group == null) return new ResponseEntity<>("group does not exist", HttpStatus.NOT_FOUND);
        if(group.getStudent().getId() != creatorId) return new ResponseEntity<>("you are not authorized person", HttpStatus.BAD_REQUEST);
        JoinRequest joinRequest = joinRequestServiceInterface.getRequestByGroupIdStudentId(groupId, stuId);
        joinRequest.setStatus(JoinRequest.JoinRequestStatus.REJECTED);
        joinRequestServiceInterface.save(joinRequest);
        return new ResponseEntity<>("request rejected", HttpStatus.OK);
    }


}
