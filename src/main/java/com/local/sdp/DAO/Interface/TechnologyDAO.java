package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Technologies;

import java.util.List;

public interface TechnologyDAO {
    void save(Technologies technologies);
    int delete(int id);
    List<Technologies> getTechnology();
    Technologies getTechnologyById(int id);
}
