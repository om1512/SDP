package com.local.sdp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "assigned_date")
    private Date assignedDate;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;

}
