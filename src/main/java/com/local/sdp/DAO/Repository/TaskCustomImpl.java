package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.TaskCustomDAO;
import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskCustomImpl implements TaskCustomDAO {


    EntityManager entityManager;
    @Autowired
    TaskCustomImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Task> findTaskByGroupId(int groupId) {
        TypedQuery<Task> taskTypedQuery = entityManager.createQuery("FROM Task t WHERE t.group.id =:id", Task.class);
        taskTypedQuery.setParameter("id", groupId);
        return taskTypedQuery.getResultList();
    }


}
