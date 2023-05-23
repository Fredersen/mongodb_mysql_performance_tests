package com.apiperformancetests.api.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Document(collection="class")
public class MongoClass {

    @Id
    private String id;
    private String name;
    private String level;
    private Integer studentNumber;

    public MongoClass(String name, String level, Integer studentNumber) {
        this.name = name;
        this.level = level;
        this.studentNumber = studentNumber;
    }

    public MongoClass() {
    }

    public String getId() {
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
