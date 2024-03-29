package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.FacultyChoice;

import java.util.List;

public interface FacultyChoiceServiceInterface {
    void save(FacultyChoice facultyChoice);
    List<FacultyChoice> getAllFacultyChoiceByGroup(int groupId);

    FacultyChoice getFacultyChoiceById(int id);

    List<FacultyChoice> getAll();
    FacultyChoice getFacultyChoiceById(int groupId, int facultyId);
    void remove(FacultyChoice facultyChoice);
}
