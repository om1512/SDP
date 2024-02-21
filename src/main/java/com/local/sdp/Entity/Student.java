package com.local.sdp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "student_name")
    private String name;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "roll_no")
    private String rollNumber;

    @Column(name = "admission_year")
    private Date year;
    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Result> resultList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="project_group_id")
    private Group group;


    public List<JoinRequest> getJoinRequestList() {
        return joinRequestList;
    }

    public void setJoinRequestList(List<JoinRequest> joinRequestList) {
        this.joinRequestList = joinRequestList;
    }

    @OneToMany(mappedBy = "student", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<JoinRequest> joinRequestList = new ArrayList<>();

    public Student() {
    }

    public Student(String name, Date year, String phone, User user, List<Result> resultList, Group group, List<JoinRequest> joinRequestList) {
        this.name = name;
        this.year = year;
        this.phone = phone;
        this.user = user;
        this.resultList = resultList;
        this.group = group;
        this.joinRequestList = joinRequestList;
    }

    public Student(int id, String name, String studentId, String rollNumber, Date year, String phone, User user, List<Result> resultList, Group group, List<JoinRequest> joinRequestList) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.year = year;
        this.phone = phone;
        this.user = user;
        this.resultList = resultList;
        this.group = group;
        this.joinRequestList = joinRequestList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", year=" + year +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                ", resultList=" + resultList +
                ", group=" + group +
                ", joinRequestList=" + joinRequestList +
                '}';
    }
}
