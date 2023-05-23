package com.apiperformancetests.api.repository.mysql;

import com.apiperformancetests.api.model.mysql.MysqlClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlClassRepository extends JpaRepository<MysqlClass, Long> { }