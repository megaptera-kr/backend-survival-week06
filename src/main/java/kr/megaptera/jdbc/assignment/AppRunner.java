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
        // post , comment relation

        // Create post table
        String createPostTableQuery = "CREATE TABLE IF NOT EXISTS post (" +
                "id SERIAL PRIMARY KEY, " +
                "title VARCHAR(255), " +
                "author VARCHAR(255), " +
                "content VARCHAR(255)" +
                ")";
        jdbcTemplate.execute(createPostTableQuery);

        // comment fk = post.id(pk)
        String createCommentTableQuery = "CREATE TABLE IF NOT EXISTS comment (" +
                "id SERIAL PRIMARY KEY, " +
                "content VARCHAR(255), " +
                "author VARCHAR(255), " +
                "post_id INT, " +
                "FOREIGN KEY (post_id) REFERENCES post(id)" +
                ")";
        jdbcTemplate.execute(createCommentTableQuery);

    }
}
