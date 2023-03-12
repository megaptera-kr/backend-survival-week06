package kr.megaptera.jdbc.assignment;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        String sql = """
                CREATE TABLE IF NOT EXISTS posts (
                    id varchar(20) NOT NULL,
                    title varchar(255) NOT NULL,
                    author varchar(100) NOT NULL,
                    content text NOT NULL,
                    PRIMARY KEY(id)
                );
                                
                CREATE TABLE IF NOT EXISTS comments (
                    id varchar(20) NOT NULL,
                    postId varchar(20) NOT NULL,
                    author varchar(100) NOT NULL,
                    content text NOT NULL,
                    PRIMARY KEY(id),
                    CONSTRAINT fk_post_id FOREIGN KEY (postId) REFERENCES posts(id) ON DELETE CASCADE
                );
            """;
        jdbcTemplate.execute(sql);
    }
}
