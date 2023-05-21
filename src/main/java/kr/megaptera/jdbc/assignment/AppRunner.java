package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

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
