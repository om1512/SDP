package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Student;

import java.util.List;

public interface StudentServiceInterface {
    void save(Student student);
    List<Student> findAll();
    Student findById(int id);
    void deleteById(int id);
    Student findByUserId(int id);

}
