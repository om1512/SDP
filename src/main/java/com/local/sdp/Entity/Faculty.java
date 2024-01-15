package com.local.sdp.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
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


    @ManyToMany(mappedBy = "facultyTechnologySet",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<Technologies> technologiesSet = new HashSet<>();


    @ManyToMany(mappedBy = "facultyDomainSet",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<Domain> domainSet = new HashSet<>();

    public Faculty() {
    }

    public Faculty(String name, int experience, String phone) {
        this.name = name;
        this.experience = experience;
        this.phone = phone;
    }


    public Set<Technologies> getTechnologiesSet() {
        return technologiesSet;
    }

    public void setTechnologiesSet(Set<Technologies> technologiesSet) {
        this.technologiesSet = technologiesSet;
    }

    public Set<Domain> getDomainSet() {
        return domainSet;
    }

    public void setDomainSet(Set<Domain> domainSet) {
        this.domainSet = domainSet;
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
                ", technologiesSet=" + technologiesSet +
                ", domainSet=" + domainSet +
                '}';
    }


}
