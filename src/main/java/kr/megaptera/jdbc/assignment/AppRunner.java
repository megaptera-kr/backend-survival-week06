package kr.megaptera.jdbc.assignment;

import org.springframework.boot.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.support.*;

@Component
public class AppRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate,
                     TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void run(String... args) {
        // TODO: posts 와 comments 테이블을 생성해 주세요.
        String query = """
                CREATE TABLE IF NOT EXISTS posts(
                id VARCHAR(30),
                title VARCHAR(50),
                author VARCHAR(20),
                content text,
                PRIMARY KEY (id)
                );
                                
                CREATE TABLE IF NOT EXISTS comments(
                id VARCHAR(30),
                postId VARCHAR(30),
                author VARCHAR(20),
                content text,
                PRIMARY KEY (id)
                );
                                
                """;
        jdbcTemplate.execute(query);
    }
}
