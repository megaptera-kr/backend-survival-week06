package kr.megaptera.jdbc.assignment;

import kr.megaptera.jdbc.assignment.utils.SchemaUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

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
        List<String> sqls = SchemaUtil.getCreateQueries();
        transactionTemplate.execute(status -> {
            sqls.forEach(sql -> jdbcTemplate.execute(sql));
            return null;
        });

    }
}
