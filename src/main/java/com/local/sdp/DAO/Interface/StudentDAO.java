package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);
    List<Student> findAll();
    Student findById(int id);

    Student findByUserId(int id);
    void deleteById(int id);
}
