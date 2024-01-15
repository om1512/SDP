package com.local.sdp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domain")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "domain_faculty", joinColumns = @JoinColumn(name = "faculty_id"), inverseJoinColumns = @JoinColumn(name = "domain_id"))
    Set<Faculty> facultyDomainSet = new HashSet<>();

    public Domain() {
    }

    public Domain(String name) {
        this.name = name;
    }

    public Domain(String name, Set<Faculty> facultyDomainSet) {
        this.name = name;
        this.facultyDomainSet = facultyDomainSet;
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

    public Set<Faculty> getFacultyDomainSet() {
        return facultyDomainSet;
    }

    public void setFacultyDomainSet(Set<Faculty> facultyDomainSet) {
        this.facultyDomainSet = facultyDomainSet;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyDomainSet=" + facultyDomainSet +
                '}';
    }
}
