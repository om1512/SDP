package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.StudentDAO;
import com.local.sdp.Entity.Student;
import com.local.sdp.Services.Interface.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements StudentServiceInterface {

    StudentDAO studentDAO;

    @Autowired
    StudentService(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }


    @Override
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }

    @Override
    public Student findByUserId(int id) {
        return studentDAO.findByUserId(id);
    }
}
