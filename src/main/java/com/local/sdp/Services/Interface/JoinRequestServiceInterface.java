package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.JoinRequest;

import java.util.List;

public interface JoinRequestServiceInterface {
    void save(JoinRequest joinRequest);
    int delete(int id);
    List<JoinRequest> getRequest();
    JoinRequest getRequestById(int id);
    List<JoinRequest> allRequestOfStudent(int studentId);
    List<JoinRequest> allRequestOfGroup(int groupId);
    JoinRequest getRequestByGroupIdStudentId(int groupId, int studentId);
    List<JoinRequest> allRequestOfGroupStudentRequested(int groupId);
    void deleteByStudentIdAndGroupId(int studentId, int groupId);


}
