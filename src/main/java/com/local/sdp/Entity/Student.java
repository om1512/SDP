package com.local.sdp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "admission_year")
    private Date year;
    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private User user;


    @ManyToMany(mappedBy = "studentSet",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Result> resultSet = new HashSet<>();


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="project_group_id")
    private Group group;

    public Student(String name, Date year, String phone, User user, Set<Result> resultSet) {
        this.name = name;
        this.year = year;
        this.phone = phone;
        this.user = user;
        this.resultSet = resultSet;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student(String name, Date year, String phone, User user, Set<Result> resultSet, Group group) {
        this.name = name;
        this.year = year;
        this.phone = phone;
        this.user = user;
        this.resultSet = resultSet;
        this.group = group;
    }

    public Set<Result> getResultSet() {
        return resultSet;
    }

    public void setResultSet(Set<Result> resultSet) {
        this.resultSet = resultSet;
    }

    public Student() {
    }

    public Student(String name, Date year, String phone) {
        this.name = name;
        this.year = year;
        this.phone = phone;
    }

    public Student(int id, String name, Date year, String phone, User user) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.phone = phone;
        this.user = user;
    }

    public Student(String name, Date year, String phone, User user) {
        this.name = name;
        this.year = year;
        this.phone = phone;
        this.user = user;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                ", resultSet=" + resultSet +
                ", group=" + group +
                '}';
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

}
