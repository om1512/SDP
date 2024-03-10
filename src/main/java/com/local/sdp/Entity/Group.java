package com.local.sdp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Join;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "project_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_name")
    private String groupName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "creator_id")
    private Student student;

    @Column(name = "group_year")
    private int year;

    @Column(name = "group_rank")
    private int rank;

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

    @OneToMany(mappedBy = "group", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Task> tasks = new ArrayList<>();

}
