package com.apiperformancetests.api.controller;

import com.apiperformancetests.api.model.mongo.MongoClass;
import com.apiperformancetests.api.model.mysql.MysqlClass;
import com.apiperformancetests.api.repository.mongo.MongoClassRepository;
import com.apiperformancetests.api.repository.mysql.MysqlClassRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/tests")
public class TestController {
    private final MongoClassRepository mongoClassRepository;
    private final MysqlClassRepository mysqlClassRepository;

    public TestController(MongoClassRepository mongoClassRepository, MysqlClassRepository mysqlClassRepository) {
        this.mongoClassRepository = mongoClassRepository;
        this.mysqlClassRepository = mysqlClassRepository;
    }

    @GetMapping
    public Map<String, Long> customizedTests(
            @RequestParam("method") String method,
            @RequestParam(value = "iterations", defaultValue = "1") int iterations
    ) {
        switch (method) {
            case "get" -> {
                return this.get(iterations);
            }
            case "post" -> {
                return this.post(iterations);
            }
            case "put" -> {
                return this.put(iterations);
            }
            case "delete" -> {
                return this.delete(iterations);
            }
        }
        throw new IllegalArgumentException("Invalid method");
    }

    private void clearDatabase() {
        mongoClassRepository.deleteAll();
        mysqlClassRepository.deleteAll();
    }

    private void dataProvider(int iterations) {
        createMongoClass(iterations);
        createMysqlClass(iterations);
    }

    private Map<String, Long> get(int iterations) {
        clearDatabase();
        dataProvider(iterations);
        long mongoDuration = measureExecutionTime(mongoClassRepository::findAll);
        long mysqlDuration = measureExecutionTime(mysqlClassRepository::findAll);

        Map<String, Long> result = new HashMap<>();
        result.put("mongo", mongoDuration);
        result.put("mysql", mysqlDuration);

        return result;
    }

    private Map<String, Long> post(int iterations) {
        clearDatabase();
        long mongoDuration = measureExecutionTime(() ->
                createMongoClass(iterations));
        long mysqlDuration = measureExecutionTime(() ->
                createMysqlClass(iterations));

        Map<String, Long> result = new HashMap<>();
        result.put("mongo", mongoDuration);
        result.put("mysql", mysqlDuration);

        return result;
    }

    private Map<String, Long> delete(int iterations) {
        clearDatabase();
        dataProvider(iterations);

        Long mongoDuration = measureExecutionTime(mongoClassRepository::deleteAll);
        Long mysqlDuration = measureExecutionTime(mysqlClassRepository::deleteAll);

        Map<String, Long> result = new HashMap<>();
        result.put("mongo", mongoDuration);
        result.put("mysql", mysqlDuration);

        return result;
    }

    private Map<String, Long> put(int iterations) {
        clearDatabase();
        dataProvider(iterations);

        // update MongoClass
        long mongoDuration = measureExecutionTime(() -> {
            List<MongoClass> mongoClasses = mongoClassRepository.findAll();
            mongoClasses.forEach(mongoClass -> {
                mongoClass.setName("newName");
                mongoClass.setLevel("CM2");
                mongoClass.setStudentNumber(21);
            });
            mongoClassRepository.saveAll(mongoClasses); // save all in one go
        });

        // update MysqlClass
        long mysqlDuration = measureExecutionTime(() -> {
            List<MysqlClass> mysqlClasses = mysqlClassRepository.findAll();
            mysqlClasses.forEach(mysqlClass -> {
                mysqlClass.setName("newName");
                mysqlClass.setLevel("CM2");
                mysqlClass.setStudentNumber(21);
            });
            mysqlClassRepository.saveAll(mysqlClasses); // save all in one go
        });

        Map<String, Long> result = new HashMap<>();
        result.put("mongo", mongoDuration);
        result.put("mysql", mysqlDuration);

        return result;
    }


    private long measureExecutionTime(Runnable operation) {
        long startTime = System.currentTimeMillis();
            operation.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private void createMongoClass(int iterations) {
        List<MongoClass> mongoClasses = IntStream.range(0, iterations).mapToObj(i -> new MongoClass(
                "name" + i,
                "CM1",
                20
        )).collect(Collectors.toList());

        mongoClassRepository.saveAll(mongoClasses);
    }

    private void createMysqlClass(int iterations) {
        List<MysqlClass> mysqlClasses = IntStream.range(0, iterations).mapToObj(i -> new MysqlClass(
                "name" + i,
                "CM1",
                20
        )).collect(Collectors.toList());

        mysqlClassRepository.saveAll(mysqlClasses);
    }
}
