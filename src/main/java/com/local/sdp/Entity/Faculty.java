package com.local.sdp.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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

    @Column(name = "available") // New field
    private boolean available; // New field


    @Column(name = "work_load")
    private int workLoad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private User user;


    @ManyToMany(mappedBy = "facultyTechnologySet",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<Technologies> technologiesSet = new HashSet<>();


    @ManyToMany(mappedBy = "facultyDomainSet",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<Domain> domainSet = new HashSet<>();

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Group> groupList  = new ArrayList<>();
}
