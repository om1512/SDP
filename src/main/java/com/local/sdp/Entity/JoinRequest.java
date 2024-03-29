package com.local.sdp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "join_request")
public class JoinRequest {

    public enum JoinRequestStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int requestId;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JoinRequestStatus status;

    @JoinColumn(name = "studentRequested")
    private boolean studentRequested;

    public boolean isStudentRequested() {
        return studentRequested;
    }

    public void setStudentRequested(boolean studentRequested) {
        this.studentRequested = studentRequested;
    }

    public JoinRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public JoinRequestStatus getStatus() {
        return status;
    }

    public void setStatus(JoinRequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JoinRequest{" +
                "requestId=" + requestId +
                ", student=" + student +
                ", group=" + group +
                ", status=" + status +
                ", sendByStudent=" + studentRequested +
                '}';
    }

}

