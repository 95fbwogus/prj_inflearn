package com.example.postgrestest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PostgreSQLRunner implements ApplicationRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbccTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("테스트 시작");
        log.info("Application Runner is get started");
        try(Connection connection = dataSource.getConnection()) {
            log.info("Start DB Connection");
            log.debug(String.valueOf(dataSource.getClass()));
            log.debug(connection.getMetaData().getURL());
            log.debug(connection.getMetaData().getUserName());

            //Statement statement = connection.createStatement();
            //String sql = "SELECT * FROM TB_USER_INFO01M01";
            //tatement.executeQuery(sql);
        }
        System.out.println("테스트");
        //jdbccTemplate.execute("INSERT INTO SLDBA.TB_USER_INFO01M01 VALUES('10220635', 'fbwogus', 'fb135456', '1', 'fwog', '010', null, sysdate, '10220635')");
    }
}
