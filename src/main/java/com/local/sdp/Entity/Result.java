package com.local.sdp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sem_number")
    private int semNo;
    @Column(name = "spi")
    private double spi;
    @Column(name = "cpi")
    private double cpi;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Result() {
    }

    public Result(int semNo, double spi, double cpi) {
        this.semNo = semNo;
        this.spi = spi;
        this.cpi = cpi;
    }


    public Result(int semNo, double spi, double cpi, Student student) {
        this.semNo = semNo;
        this.spi = spi;
        this.cpi = cpi;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemNo() {
        return semNo;
    }

    public void setSemNo(int semNo) {
        this.semNo = semNo;
    }

    public double getSpi() {
        return spi;
    }

    public void setSpi(double spi) {
        this.spi = spi;
    }

    public double getCpi() {
        return cpi;
    }

    public void setCpi(double cpi) {
        this.cpi = cpi;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", semNo=" + semNo +
                ", spi=" + spi +
                ", cpi=" + cpi +
                ", student=" + student +
                '}';
    }
}
