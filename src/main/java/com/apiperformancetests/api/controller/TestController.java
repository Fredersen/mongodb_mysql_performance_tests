package com.apiperformancetests.api.controller;

import com.apiperformancetests.api.model.mongodb.MongoUser;
import com.apiperformancetests.api.model.mysql.MysqlUser;
import com.apiperformancetests.api.repository.mongo.MongoUserRepository;
import com.apiperformancetests.api.repository.mysql.MysqlUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final MongoUserRepository mongoUserRepository;

    private final MysqlUserRepository mysqlUserRepository;

    public TestController(MongoUserRepository mongoUserRepository, MysqlUserRepository mysqlUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
        this.mysqlUserRepository = mysqlUserRepository;
    }

    @GetMapping
    public Map<String, Long> customizedTests(
            @RequestParam("method") String method,
            @RequestParam("iterations") int iterations
    ) {
        switch (method) {
            case "get":
                return this.get(iterations);
            case "post":
                return this.post(iterations);
            case "put":
                // return this.put(iterations);
            case "delete":
                // return this.delete(iterations);
        }
        throw new IllegalArgumentException("Invalid method");
    }

    private void clearDatabase() {
        mongoUserRepository.deleteAll();
        mysqlUserRepository.deleteAll();
    }

    private Map<String, Long> get(int iterations) {
        clearDatabase();
        long mongoDuration = measureExecutionTime(iterations, mongoUserRepository::findAll);
        long mysqlDuration = measureExecutionTime(iterations, mysqlUserRepository::findAll);

        Map<String, Long> result = new HashMap<>();
        result.put("mongo", mongoDuration);
        result.put("mysql", mysqlDuration);

        return result;
    }

    private Map<String, Long> post(int iterations) {
        clearDatabase();
        long mongoDuration = measureExecutionTime(iterations, () ->
                IntStream.range(0, iterations).forEach(i -> {
                    MongoUser user = new MongoUser("MongoUser" + i, "mongoUser" + i + "@example.com");
                    mongoUserRepository.save(user);
                })
        );
        long mysqlDuration = measureExecutionTime(iterations, () ->
                IntStream.range(0, iterations).forEach(i -> {
                    MysqlUser user = new MysqlUser("MysqlUser" + i, "mysqlUser" + i + "@example.com");
                    mysqlUserRepository.save(user);
                })
        );

        Map<String, Long> result = new HashMap<>();
        result.put("mongo", mongoDuration);
        result.put("mysql", mysqlDuration);

        return result;
    }

    private long measureExecutionTime(int iterations, Runnable operation) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            operation.run();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
