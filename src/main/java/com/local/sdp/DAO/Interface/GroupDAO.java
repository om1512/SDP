package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Group;

import java.util.List;

public interface GroupDAO {
    void save(Group group);
    int delete(int id);
    List<Group> groupList();
    Group getGroupById(int id);
}
