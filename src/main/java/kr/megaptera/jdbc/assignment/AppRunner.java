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

        transactionTemplate.execute(status -> {
            String sql = """
                    CREATE TABLE IF NOT EXISTS testpost (     
                      id             char(255) primary key,   
                      title          char(255),   
                      author         char(255),      
                      content        char(255)
                    );
                    """;

            String sql2 = """
                    CREATE TABLE IF NOT EXISTS testcomment (     
                      id             char(255) primary key,   
                      postid         char(255), 
                      author         char(255),      
                      content        char(255)
                    );
                    """;

            jdbcTemplate.execute(sql);
            jdbcTemplate.execute(sql2);

            return null;
        });
    }
}
