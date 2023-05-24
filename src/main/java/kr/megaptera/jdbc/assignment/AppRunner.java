package kr.megaptera.jdbc.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void run(String... args) {
        // TODO: posts 와 comments 테이블을 생성해 주세요.
        String query = """
                CREATE TABLE if not exists posts
                (id varchar(30),
                title varchar(30),
                author varchar(30),
                content varchar(500)
                );
                """;
        jdbcTemplate.execute(query);

        String query2 = """
                CREATE TABLE if not exists comments( 
                id varchar(30),
                author varchar(30),
                content varchar(500),
                postid varchar(30)
                );
                """;
        jdbcTemplate.execute(query2);
    }
}
