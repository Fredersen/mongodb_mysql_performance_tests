package com.apiperformancetests.api.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<com.apiperformancetests.api.model.mongodb.MongoUser, String> { }

