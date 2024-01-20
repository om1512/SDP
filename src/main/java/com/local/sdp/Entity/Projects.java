package com.local.sdp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id")
    private Group projectGroup;

    @Column(name = "is_custom")
    private boolean isCustom;


    public Projects() {
    }

    public Projects(String name, String description, boolean isCustom) {
        this.name = name;
        this.description = description;
        this.isCustom = isCustom;
    }

    public Projects(String name, String description, Group projectGroup, boolean isCustom) {
        this.name = name;
        this.description = description;
        this.projectGroup = projectGroup;
        this.isCustom = isCustom;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Group getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(Group projectGroup) {
        this.projectGroup = projectGroup;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    @Override
    public String
    toString() {
        return "Projects{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectGroup=" + projectGroup +
                ", isCustom=" + isCustom +
                '}';
    }
}
