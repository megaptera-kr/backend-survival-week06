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
        // TODO: posts 와 comments 테이블을 생성해 주세요.
        transactionTemplate.execute((status) -> {
                String query = """
                        DROP TABLE IF EXISTS posts;
                        
                        CREATE TABLE posts(
                            id VARCHAR(100),
                            title VARCHAR(100),
                            author VARCHAR(30),
                            content VARCHAR(1000) 
                        );
                        
                        DROP TABLE IF EXISTS comments;
                        CREATE TABLE comments(
                          id VARCHAR(100),
                          post_id VARCHAR(100),
                            author VARCHAR(30),
                            content VARCHAR(1000) 
                        );
                        
                        """;
            jdbcTemplate.update(query);

            return null;
        });
    }
}
