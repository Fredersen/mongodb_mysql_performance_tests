package com.apiperformancetests.api.controller;

import com.apiperformancetests.api.model.mysql.MysqlUser;
import com.apiperformancetests.api.repository.mysql.MysqlUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mysql/users")
public class MysqlUserController {
    private final MysqlUserRepository repository;

    public MysqlUserController(MysqlUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MysqlUser> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping
    public MysqlUser createUser(@RequestBody MysqlUser user) {
        return repository.save(user);
    }
}

