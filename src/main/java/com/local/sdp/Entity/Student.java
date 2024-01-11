package com.local.sdp.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Student")
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


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
