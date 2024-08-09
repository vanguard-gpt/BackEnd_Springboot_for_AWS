package com.restapi.emp.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
@Order(1)
@Slf4j
public class DatabaseRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("DataSource 구현객체는 {} ", dataSource.getClass().getName());

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("DB 이름 = {}", metaData.getDatabaseProductName());
            log.info("DB URL 주소 = {}", metaData.getURL());
            log.info("DB 접속 유저 이름 = {}", metaData.getUserName());
        }

        log.info("Arguments 정보");
        args.getOptionNames().forEach(System.out::println);
    }
}