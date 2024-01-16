package com.local.sdp.Services.Repository;


import com.local.sdp.DAO.Interface.ResultDAO;
import com.local.sdp.Entity.Result;
import com.local.sdp.Services.Interface.ResultServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService implements ResultServiceInterface {

    ResultDAO resultDAO;
    @Autowired
    ResultService(ResultDAO resultDAO){
        this.resultDAO = resultDAO;
    }

    @Override
    public void save(Result result) {
        resultDAO.save(result);
    }

    @Override
    public int delete(int id) {
        return resultDAO.delete(id);
    }

    @Override
    public List<Result> getResult() {
        return resultDAO.getResult();
    }

    @Override
    public Result getResultById(int id) {
        return resultDAO.getResultById(id);
    }
}
