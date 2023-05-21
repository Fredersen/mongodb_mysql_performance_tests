package com.apiperformancetests.api;

import com.apiperformancetests.api.model.mongodb.MongoUser;
import com.apiperformancetests.api.model.mysql.MysqlUser;
import com.apiperformancetests.api.repository.mongo.MongoUserRepository;
import com.apiperformancetests.api.repository.mysql.MysqlUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PerformanceTests {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Autowired
    private MysqlUserRepository mysqlUserRepository;

    @BeforeEach
    public void setup() {
        mongoUserRepository.deleteAll();
        mysqlUserRepository.deleteAll();
    }

    // Example de test pour la création de 100 users en mongodb
    @Test
    public void testMongodbPerformance() {
        long startTime = System.currentTimeMillis();

        IntStream.range(0, 100).forEach(i -> {
            MongoUser user = new MongoUser("MongoUser" + i, "mongoUser" + i + "@example.com");
            mongoUserRepository.save(user);
        });

        long endTime = System.currentTimeMillis();

        System.out.println("MongoDB Time taken: " + (endTime - startTime) + "ms");
    }

    // Example de test pour la création de 100 users en mysql
    @Test
    public void testMysqlPerformance() {
        long startTime = System.currentTimeMillis();

        IntStream.range(0, 100).forEach(i -> {
            MysqlUser user = new MysqlUser("MysqlUser" + i, "mysqlUser" + i + "@example.com");
            mysqlUserRepository.save(user);
        });

        long endTime = System.currentTimeMillis();

        System.out.println("MySQL Time taken: " + (endTime - startTime) + "ms");
    }
}