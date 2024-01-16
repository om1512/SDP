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
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "result_student", joinColumns = @JoinColumn(name = "result_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> studentSet = new HashSet<>();

    public Result() {
    }

    public Result(int semNo, double spi, double cpi) {
        this.semNo = semNo;
        this.spi = spi;
        this.cpi = cpi;
    }

    public Result(int semNo, double spi, double cpi, Set<Student> studentSet) {
        this.semNo = semNo;
        this.spi = spi;
        this.cpi = cpi;
        this.studentSet = studentSet;
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

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", semNo=" + semNo +
                ", spi=" + spi +
                ", cpi=" + cpi +
                ", studentSet=" + studentSet +
                '}';
    }
}
