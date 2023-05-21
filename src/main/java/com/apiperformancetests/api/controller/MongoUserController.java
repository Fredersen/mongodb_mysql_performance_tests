package com.apiperformancetests.api.controller;

import com.apiperformancetests.api.repository.mongo.MongoUserRepository;
import org.springframework.web.bind.annotation.*;
import com.apiperformancetests.api.model.mongodb.MongoUser;

import java.util.List;

@RestController
@RequestMapping("/api/mongodb/users")
public class MongoUserController {
    private final MongoUserRepository repository;

    public MongoUserController(MongoUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MongoUser> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping
    public MongoUser createUser(@RequestBody MongoUser user) {
        return repository.save(user);
    }

    // Add other CRUD operations as needed
}
