package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.FacultyChoice;
import com.local.sdp.Entity.ProjectChoice;

import java.util.List;

public interface FacultyChoiceDAO {
    void save(FacultyChoice facultyChoice);
    List<FacultyChoice> getAllFacultyChoiceByGroup(int groupId);
    FacultyChoice getFacultyChoiceById(int id);
    FacultyChoice getFacultyChoiceById(int groupId, int facultyId);
    List<FacultyChoice> getAll();
    void delete(int id);
}
