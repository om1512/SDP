package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Result;

import java.util.List;

public interface ResultDAO {
    void save(Result result);
    int delete(int id);

    List<Result> getResult();

    Result getResultById(int id);
}
