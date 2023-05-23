package com.apiperformancetests.api.model.mysql;

import jakarta.persistence.*;

@Entity
public class MysqlClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String level;
    private Integer studentNumber;

    public MysqlClass(String name, String level, Integer studentNumber) {
        this.name = name;
        this.level = level;
        this.studentNumber = studentNumber;
    }

    public MysqlClass() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
}

