package com.local.sdp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "faculty_name")
    private String name;
    @Column(name = "experience")
    private int experience;
    @Column(name = "phone")
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private User user;
    public Faculty() {
    }

    public Faculty(String name, int experience, String phone) {
        this.name = name;
        this.experience = experience;
        this.phone = phone;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
