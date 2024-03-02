package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.PhaseDAO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class PhaseImpl implements PhaseDAO {

    @Autowired
    EntityManager entityManager;
    @Override
    public String sayHi() {
        return "Hi From OM";
    }
}
