package com.apiperformancetests.api.model.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class MysqlStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String situation;

    @ManyToOne
    @JoinColumn(name = "mysql_class_id")
    @JsonIgnore
    private MysqlClass classId;

    public MysqlStudent(String firstName, String lastName, MysqlClass classId, String situation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.situation = situation;
    }

    public MysqlStudent() {
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setClassId(MysqlClass classId) {
        this.classId = classId;
    }

    public MysqlClass getClassId() {
        return this.classId;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getSituation() {
        return this.situation;
    }
}
