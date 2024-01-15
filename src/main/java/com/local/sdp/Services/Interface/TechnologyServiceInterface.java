package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Technologies;

import java.util.List;

public interface TechnologyServiceInterface {
    void save(Technologies technologies);
    int delete(int id);
    List<Technologies> getTechnology();
    Technologies getTechnologyById(int id);
}
