package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.JoinRequestDAO;
import com.local.sdp.Entity.JoinRequest;
import com.local.sdp.Services.Interface.JoinRequestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinRequestService implements JoinRequestServiceInterface {
    JoinRequestDAO joinRequestDAO;
    @Autowired
    JoinRequestService(JoinRequestDAO joinRequestDAO){
        this.joinRequestDAO = joinRequestDAO;
    }
    @Override
    public void save(JoinRequest joinRequest) {
        joinRequestDAO.save(joinRequest);
    }
    @Override
    public int delete(int id) {
        return joinRequestDAO.delete(id);
    }
    @Override
    public List<JoinRequest> getRequest() {
        return joinRequestDAO.getRequest();
    }
    @Override
    public JoinRequest getRequestById(int id) {
        return joinRequestDAO.getRequestById(id);
    }
    @Override
    public List<JoinRequest> allRequestOfStudent(int studentId) {
        return joinRequestDAO.allRequestOfStudent(studentId);
    }
    @Override
    public List<JoinRequest> allRequestOfGroup(int groupId) {
        return joinRequestDAO.allRequestOfGroup(groupId);
    }
    @Override
    public JoinRequest getRequestByGroupIdStudentId(int groupId, int studentId) {
        return joinRequestDAO.getRequestByGroupIdStudentId(groupId, studentId);
    }
    @Override
    public List<JoinRequest> allRequestOfGroupStudentRequested(int groupId) {
        return joinRequestDAO.allRequestOfGroupStudentRequested(groupId);
    }
}
