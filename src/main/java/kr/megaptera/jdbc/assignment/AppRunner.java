package kr.megaptera.jdbc.assignment;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.*;
import org.springframework.transaction.support.*;

public class AppRunner implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void run(String... args) {
        transactionTemplate.execute(status -> {
            String query = """
            CREATE TABLE IF NOT EXISTS posts (
            id varchar(255) NOT NULL UNIQUE,
            title varchar(255) NOT NULL,
            author varchar(255) NOT NULL,
            content text NOT NULL
            )
            """;

            jdbcTemplate.execute(query);

            return null;
        });

        transactionTemplate.execute(status -> {
            String query = """
            CREATE TABLE IF NOT EXISTS comments (
            id varchar(255) NOT NULL UNIQUE,
            post_id varchar(255) NOT NULL,
            author varchar(255) NOT NULL,
            content text NOT NULL
            )
            """;

            jdbcTemplate.execute(query);

            return null;
        });
    }
}
