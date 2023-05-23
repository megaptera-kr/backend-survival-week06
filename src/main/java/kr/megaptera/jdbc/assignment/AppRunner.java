package kr.megaptera.jdbc.assignment;

import org.springframework.boot.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

@Component
public class AppRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        String[] sql = {"CREATE TABLE IF NOT EXISTS posts (id VARCHAR(100)," +
                " title VARCHAR(100), author VARCHAR(100), content text)",
                "CREATE TABLE IF NOT EXISTS comments(id VARCHAR(100), postid VARCHAR(100), " +
                        "author VARCHAR(100), content text)"};
        jdbcTemplate.batchUpdate(sql);
    }
}
