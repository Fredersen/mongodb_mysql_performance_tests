package com.apiperformancetests.api.repository.mysql;

import com.apiperformancetests.api.model.mysql.MysqlUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlUserRepository extends JpaRepository<MysqlUser, Long> { }
