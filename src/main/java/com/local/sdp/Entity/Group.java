package com.local.sdp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_name")
    private String groupName;

    @JsonIgnoreProperties({"group"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "creator_id")
    private Student student;

    @Column(name = "group_year")
    private int year;

    @Column(name = "group_rank")
    private int rank;

    @JsonIgnoreProperties({"group"})
    @OneToMany(mappedBy = "group", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Student> studentList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="project_guide_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<JoinRequest> joinRequestList  = new ArrayList<>();

    @OneToMany(mappedBy = "projectGroup", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Projects> customProjectList = new ArrayList<>();

    // allocated project
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "allocated_project_id")
    private Projects project;


    public List<Projects> getCustomProjectList() {
        return customProjectList;
    }

    public void setCustomProjectList(List<Projects> customProjectList) {
        this.customProjectList = customProjectList;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<JoinRequest> getJoinRequestList() {
        return joinRequestList;
    }

    public void setJoinRequestList(List<JoinRequest> joinRequestList) {
        this.joinRequestList = joinRequestList;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public Group(String groupName, Student student, int year, int rank, List<Student> studentList, Faculty faculty) {
        this.groupName = groupName;
        this.student = student;
        this.year = year;
        this.rank = rank;
        this.studentList = studentList;
        this.faculty = faculty;
    }

    public Group(String groupName, Student student, int year) {
        this.groupName = groupName;
        this.student = student;
        this.year = year;
    }

    public Group(String groupName, int year) {
        this.groupName = groupName;
        this.year = year;
    }

    public Group(String groupName, Student student, int year, int rank, List<Student> studentList) {
        this.groupName = groupName;
        this.student = student;
        this.year = year;
        this.rank = rank;
        this.studentList = studentList;
    }

    public Group(String groupName, Student student, int year, int rank) {
        this.groupName = groupName;
        this.student = student;
        this.year = year;
        this.rank = rank;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", student=" + student +
                ", year=" + year +
                ", rank=" + rank +
                ", studentList=" + studentList +
                ", faculty=" + faculty +
                ", joinRequestList=" + joinRequestList +
                ", customProjectList=" + customProjectList +
                ", project=" + project +
                '}';
    }
}
