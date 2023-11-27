package kr.megaptera.jdbc.assignment;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DaoFactoryConfig {
    private static TransactionTemplate transactionTemplate;
    private static JdbcTemplate jdbcTemplate;


    public DaoFactoryConfig(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public PostDao postDao() {
        return new JdbcPostDao(jdbcTemplate, transactionTemplate);
    }

    @Bean
    public CommentDao commentDao() {
        return new JdbcCommentDao(jdbcTemplate, transactionTemplate);
    }
}
