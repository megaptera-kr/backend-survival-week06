package kr.megaptera.jdbc.assignment.configs;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DaoConfig {
    @Bean
    public PostDao postDao(
            TransactionTemplate transactionTemplate,
            JdbcTemplate jdbcTemplate
    ) {
        return new JdbcPostDao(transactionTemplate, jdbcTemplate);
    }

    @Bean
    CommentDao commentDao(
            TransactionTemplate transactionTemplate,
            JdbcTemplate jdbcTemplate
    ) {
        return new JdbcCommentDao(transactionTemplate, jdbcTemplate);
    }
}
