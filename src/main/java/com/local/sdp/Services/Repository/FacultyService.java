package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.FacultyDAO;
import com.local.sdp.Entity.Faculty;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FacultyService implements FacultyServiceInterface {

    FacultyDAO facultyDAO;

    @Autowired
    FacultyService(FacultyDAO facultyDAO){
        this.facultyDAO = facultyDAO;
    }
    @Override
    @Transactional
    public void save(Faculty faculty) {
        facultyDAO.save(faculty);
    }

    @Override
    public List<Faculty> getFaculties() {
        return facultyDAO.getFaculties();
    }

    @Override
    public Faculty getFacultyById(int id) {
        return facultyDAO.getFacultyById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        facultyDAO.deleteById(id);
    }

    @Override
    public Faculty getFacultyByUserId(int id) {
        return facultyDAO.getFacultyByUserId(id);
    }
}
