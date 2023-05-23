package com.apiperformancetests.api.repository.mysql;

import com.apiperformancetests.api.model.mysql.MysqlStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlStudentRepository extends JpaRepository<MysqlStudent, Long> {
    // find all with join
}
