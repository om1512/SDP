package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.FacultyChoiceDAO;
import com.local.sdp.Entity.FacultyChoice;
import com.local.sdp.Services.Interface.FacultyChoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FacultyChoiceServiceService implements FacultyChoiceServiceInterface {

    FacultyChoiceDAO facultyChoiceDAO;
    @Autowired
    FacultyChoiceServiceService(FacultyChoiceDAO facultyChoiceDAO){
        this.facultyChoiceDAO = facultyChoiceDAO;
    }

    @Override
    public void save(FacultyChoice facultyChoice) {
        facultyChoiceDAO.save(facultyChoice);
    }

    @Override
    public List<FacultyChoice> getAllFacultyChoiceByGroup(int groupId) {
        return facultyChoiceDAO.getAllFacultyChoiceByGroup(groupId);
    }

    @Override
    public FacultyChoice getFacultyChoiceById(int id) {
        return facultyChoiceDAO.getFacultyChoiceById(id);
    }

    @Override
    public List<FacultyChoice> getAll() {
        return facultyChoiceDAO.getAll();
    }

    @Override
    public FacultyChoice getFacultyChoiceById(int groupId, int facultyId) {
        return facultyChoiceDAO.getFacultyChoiceById(groupId, facultyId);
    }
}
