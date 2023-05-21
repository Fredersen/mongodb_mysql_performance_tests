package com.apiperformancetests.api.model.mysql;

import jakarta.persistence.*;

@Entity
public class MysqlUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    public MysqlUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MysqlUser() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
