package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class AppRunner implements CommandLineRunner {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public AppRunner(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            System.out.println("Try creating tables...");
            jdbcTemplate.batchUpdate(
                    """
                            CREATE TABLE IF NOT EXISTS posts (
                                id varchar(16) primary key,
                                author varchar(128) not null,
                                title varchar(128) not null,
                                content varchar(1024)
                            );
                            """, """
                            CREATE TABLE IF NOT EXISTS comments (
                                id varchar(16) primary key,
                                post_id varchar(16) not null,
                                author varchar(128) not null,
                                content varchar(512)
                            );
                            """
            );
            System.out.println("Tables created or already exists");
        });
    }
}
