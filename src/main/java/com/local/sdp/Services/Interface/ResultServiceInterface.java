package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Result;
import java.util.List;

public interface ResultServiceInterface {
    void save(Result result);
    int delete(int id);
    List<Result> getResult();
    Result getResultById(int id);
}
