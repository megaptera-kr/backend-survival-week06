package kr.megaptera.jdbc.assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DBConnectTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void connect() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5436/back6week", "postgres", "password");
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
