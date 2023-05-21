package kr.megaptera.jdbc.assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;

@SpringBootTest
public class DBConnectTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void connect () {
        jdbcTemplate.query("select * from posts", resultSet -> {
           resultSet.next();
        });
    }
}
