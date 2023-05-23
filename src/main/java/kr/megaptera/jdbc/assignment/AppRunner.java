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
        // TODO: posts 와 comments 테이블을 생성해 주세요.
        String createPosts =
                "CREATE TABLE IF NOT EXISTS posts (" +
                        "   id VARCHAR(50) NOT NULL," +
                        "   title VARCHAR(255) NOT NULL," +
                        "   author VARCHAR(50) NOT NULL," +
                        "   content VARCHAR(3000) NOT NULL" +
                        ");";

        String createComments =
                "CREATE TABLE IF NOT EXISTS comments (" +
                        "   id VARCHAR(50) NOT NULL," +
                        "   post_id VARCHAR(50) NOT NULL," +
                        "   author VARCHAR(50) NOT NULL," +
                        "   content VARCHAR(3000) NOT NULL" +
                        ");";

        jdbcTemplate.update(createPosts);
        jdbcTemplate.update(createComments);
    }
}
