package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Task;

import java.util.List;

public interface TaskCustomDAO {
    List<Task> findTaskByGroupId(int groupId);
}
