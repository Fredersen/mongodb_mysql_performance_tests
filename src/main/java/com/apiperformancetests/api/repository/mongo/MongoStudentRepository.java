package com.apiperformancetests.api.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoStudentRepository extends MongoRepository<com.apiperformancetests.api.model.mongo.MongoStudent, String> { }

