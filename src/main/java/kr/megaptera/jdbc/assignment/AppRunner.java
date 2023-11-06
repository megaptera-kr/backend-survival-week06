package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private JdbcTemplate jdbcTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        String createPostTableSql = """
                CREATE TABLE IF NOT EXISTS post (
                    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                    title VARCHAR NOT NULL,
                    author VARCHAR NOT NULL,
                    content VARCHAR NOT NULL
                );
                """;
        String createCommentTableSql = """
                CREATE TABLE IF NOT EXISTS comment (
                    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                    postId VARCHAR NOT NULL,
                    author VARCHAR NOT NULL,
                    content VARCHAR NOT NULL,
                    FOREIGN KEY(id) REFERENCES post(id)
                )
                """;
        jdbcTemplate.update(createPostTableSql);
        jdbcTemplate.update(createCommentTableSql);
    }
}
