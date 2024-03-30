package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Faculty;

import java.util.List;

public interface FacultyDAO {
    void save(Faculty faculty);
    List<Faculty> getFaculties();
    Faculty getFacultyById(int id);
    Faculty getFacultyByUserId(int id);
    Faculty getFacultyByEmail(String email);
    void deleteById(int id);
}
