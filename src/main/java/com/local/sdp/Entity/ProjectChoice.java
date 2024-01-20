package com.local.sdp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_choice")
public class ProjectChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "project_id", nullable = false)
    private Projects projects;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "priority")
    private int priority;

    public ProjectChoice() {
    }

    public ProjectChoice(Projects projects, Group group, Student student) {
        this.projects = projects;
        this.group = group;
        this.student = student;
    }

    public ProjectChoice(Projects projects, Group group, Student student, int priority) {
        this.projects = projects;
        this.group = group;
        this.student = student;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ProjectChoice{" +
                "id=" + id +
                ", projects=" + projects +
                ", group=" + group +
                ", student=" + student +
                ", priority=" + priority +
                '}';
    }
}
