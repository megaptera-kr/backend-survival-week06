package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

public class AppRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        // TODO: posts 와 comments 테이블을 생성해 주세요.
        String postsTable = """
                CREATE TABLE IF NOT EXISTS POSTS (
                  ID VARCHAR(100) UNIQUE NOT NULL
                 ,TITLE VARCHAR(200) NOT NULL
                 ,AUTHOR VARCHAR(200) NOT NULL
                 ,CONTENT VARCHAR(3000) NOT NULL
                 )
                """;

        String commentsTable = """
                CREATE TABLE IF NOT EXISTS COMMENTS (
                  ID VARCHAR(100) UNIQUE NOT NULL
                 ,POST_ID VARCHAR(100) NOT NULL               
                 ,AUTHOR VARCHAR(200) NOT NULL
                 ,CONTENT VARCHAR(3000) NOT NULL
                 )
                """;

        jdbcTemplate.execute(postsTable);
        jdbcTemplate.execute(commentsTable);
    }
}
