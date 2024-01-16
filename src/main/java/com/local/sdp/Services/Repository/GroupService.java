package com.local.sdp.Services.Repository;


import com.local.sdp.DAO.Interface.GroupDAO;
import com.local.sdp.DAO.Repository.GroupImpl;
import com.local.sdp.Entity.Group;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements GroupServiceInterface {

    GroupDAO groupDAO;
    @Autowired
    GroupService(GroupDAO groupDAO){
        this.groupDAO = groupDAO;
    }
    @Override
    public void save(Group group) {
        groupDAO.save(group);
    }

    @Override
    public int delete(int id) {
        return groupDAO.delete(id);
    }

    @Override
    public List<Group> groupList() {
        return groupDAO.groupList();
    }

    @Override
    public Group getGroupById(int id) {
        return groupDAO.getGroupById(id);
    }
}
