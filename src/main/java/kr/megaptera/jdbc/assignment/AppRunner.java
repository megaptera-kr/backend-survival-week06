package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class AppRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void run(String... args) {
        transactionTemplate.execute(status -> {
            // create posts table
            try {
                String sql = """
                        CREATE TABLE IF NOT EXISTS posts (
                            id BIGINT PRIMARY KEY,
                            title VARCHAR(255) NOT NULL,
                            author VARCHAR(255) NOT NULL,
                            content TEXT NOT NULL
                        )
                        """;
                jdbcTemplate.execute(sql);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create posts table", e);
            }
            // create comments table
            try {
                String sql = """
                        CREATE TABLE IF NOT EXISTS comments (
                            id BIGINT PRIMARY KEY,
                            post_id BIGINT NOT NULL,
                            author VARCHAR(255) NOT NULL,
                            content TEXT NOT NULL,
                            FOREIGN KEY (post_id) REFERENCES posts(id)
                        )
                        """;
                jdbcTemplate.execute(sql);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create comments table", e);
            }
            return null;
        });
    }
}
