package com.local.sdp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "technologies")
public class Technologies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "technologies_faculty", joinColumns = @JoinColumn(name = "technology_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    Set<Faculty> facultyTechnologySet = new HashSet<>();

    public Technologies() {
    }

    public Technologies(String name) {
        this.name = name;
    }

    public Technologies(String name, Set<Faculty> facultyTechnologySet) {
        this.name = name;
        this.facultyTechnologySet = facultyTechnologySet;
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

    public Set<Faculty> getFacultyTechnologySet() {
        return facultyTechnologySet;
    }

    public void setFacultyTechnologySet(Set<Faculty> facultyTechnologySet) {
        this.facultyTechnologySet = facultyTechnologySet;
    }

    @Override
    public String toString() {
        return "Technologies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyTechnologySet=" + facultyTechnologySet +
                '}';
    }
}
