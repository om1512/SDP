package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Faculty;

import java.util.List;

public interface FacultyServiceInterface {
    void save(Faculty faculty);
    List<Faculty> getFaculties();
    Faculty getFacultyById(int id);
    void deleteById(int id);
    Faculty getFacultyByUserId(int id);
}
