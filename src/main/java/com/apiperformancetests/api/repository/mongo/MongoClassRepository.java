package com.apiperformancetests.api.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoClassRepository extends MongoRepository<com.apiperformancetests.api.model.mongo.MongoClass, String> { }

