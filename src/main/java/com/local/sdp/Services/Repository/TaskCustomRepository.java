package com.local.sdp.Services.Repository;


import com.local.sdp.DAO.Interface.TaskCustomDAO;
import com.local.sdp.Entity.Task;
import com.local.sdp.Services.Interface.TaskCustomServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskCustomRepository implements TaskCustomServiceInterface {

    @Autowired
    TaskCustomDAO taskCustomDAO;
    @Override
    public List<Task> findTaskByGroupId(int groupId) {
        return taskCustomDAO.findTaskByGroupId(groupId);
    }
}
