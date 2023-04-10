package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        String sql = """
                CREATE TABLE IF NOT EXISTS posts (
                    id VARCHAR(30),
                    title VARCHAR(50),
                    author VARCHAR(30),
                    content VARCHAR(200),
                    PRIMARY KEY (id)
                );
                
                CREATE TABLE IF NOT EXISTS comments (
                    id VARCHAR(30),
                    post_id VARCHAR(30),
                    author VARCHAR(30),
                    content VARCHAR(200),
                    PRIMARY KEY (id),
                    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
                );
                """;
        jdbcTemplate.execute(sql);
    }
}
